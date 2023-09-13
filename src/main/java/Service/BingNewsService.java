package Service;

import Configuration.*;
import Configuration.Weather.MappingConfig;
import Configuration.Weather.WeatherConfig;
import Model.Articles;
import Model.TopNews;
import Model.WeatherData;
import Model.TimeZone;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static Service.NewsApiReader.getResponse;

public class BingNewsService {

    public static <T> T readConfig(String jsonPath, Class<T> classConfig) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(jsonPath), classConfig);
    }

    private static List<Channel> getListChannel(BingNewsConfig bingNewsConfig) {
        var categories = bingNewsConfig.getCategories();
        //loop each category, get each channel, get each URL
        List<Channel> listChannels = new ArrayList<>();
        for (var cat : categories) {
            for (var channel : cat.getChannels()) {
                listChannels.add(channel);
            }
        }
        return listChannels;
    }

    public static NodeList getItemsFromRssUrl(String rssUrl) throws ParserConfigurationException, IOException, SAXException {
        URL url = new URL(rssUrl);
        try (InputStream inputStream = url.openStream()) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(inputStream);
            doc.getDocumentElement().normalize();
            return doc.getElementsByTagName("item");
        }
    }

    public static List<Articles> getAllArticles(BingNewsConfig bingNewsConfig, Configuration.MappingConfig propertyMapConfig) throws Exception {
        List<Channel> listChannel = getListChannel(bingNewsConfig);
        List<Articles> listArticle = new ArrayList<>();

        for (var channel : listChannel) {
            NodeList nodeList = getItemsFromRssUrl(channel.getRSSURL());

            List<PropertyMapping> propertyMappings = propertyMapConfig.getChannels()
                    .stream()
                    .filter(x -> x.getChannelName().equals(channel.getChannelName()))
                    .findFirst()
                    .map(Channel::getPropertyMappings)
                    .orElse(Collections.emptyList());

            for (int i = 0; i < nodeList.getLength(); i++) {
                channel.setPropertyMappings(propertyMappings);
                Articles atc = mapArticles(nodeList.item(i), channel, Articles.class);
                listArticle.add(atc);
            }
        }
        return listArticle;
    }

    public static <T> T mapArticles(Node item, Channel channel, Class<T> classTarget) throws Exception {
        T instance = classTarget.newInstance();

        if (item.getNodeType() == Node.ELEMENT_NODE) {
            Element itemElement = (Element) item;
            for (var propertyMap : channel.getPropertyMappings()) {
                String propertyName = propertyMap.getPropertyName();
                String tagName = propertyMap.getTagName();
                NodeList nodeList = itemElement.getElementsByTagName(tagName);

                // Check if the nodeList is empty or the item is null
                if (nodeList.getLength() > 0 && nodeList.item(0) != null) {
                    String value = nodeList.item(0).getTextContent();
                    setPropertyValue(instance, propertyName, value);
                }
            }
        }
        return instance;
    }

    public static void setPropertyValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static List<TopNews> mapTopNews(String response, MapTopNews topNewsConfg) throws Exception {
        List<TopNews> listAtc = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        rootNode = rootNode.get(topNewsConfg.getResult());

        for (var articleNode : rootNode) {
            TopNews topNews = new TopNews();
            for (var item : topNewsConfg.getMapping()) {
                String value = articleNode.findPath(item.getTagName()).asText();
                setPropertyValue(topNews, item.getPropertyName(), value);
            }
            listAtc.add(topNews);
        }
        return listAtc;
    }

    public static List<TopNews> getTopNews(TopNewsConfig config) throws Exception {
        var listAPI = config.getTopNewsConfig();
        var listNews = new ArrayList<TopNews>();
        for (var api : listAPI) {
            var uri = api.getURI();
            var items = getResponse(URI.create(uri));
            var list = mapTopNews(items.body(), api);
            listNews.addAll(list);
        }
        return listNews;
    }

    public static String setLocation(Float latitude, Float longitude, WeatherConfig weatherConfig) throws Exception {
        WeatherConfig config = readConfig("src/main/resources/WeatherConfig.json", WeatherConfig.class);

        String API_URL = "https://api.open-meteo.com/v1/forecast";
        String apiUrl = String.format("%s?latitude=%.6f&longitude=%.6f&hourly=%s&past_days=%s&forecast_days=%s", API_URL,
                latitude, longitude, config.getDefaultParameters().getHourly(), config.getDefaultParameters().getPastDays(), config.getDefaultParameters().getForecastDays());
        return apiUrl;
    }

    public static WeatherData getWeatherInfo(Float latitude, Float longitude, WeatherConfig config) throws Exception {
        String url = setLocation(latitude, longitude, config);
        HttpResponse<String> apiUrl = NewsApiReader.getResponse(URI.create(url));

        var currentWeather = new WeatherData();
        currentWeather = mapWeatherData(apiUrl.body(), config.getCurrentWeatherConfig());
        return currentWeather;
    }

    public static WeatherData mapWeatherData(String apiResponse, MappingConfig mappingConfig) throws Exception {
        //List<TopNews> listAtc = new ArrayList<>();
        var weatherInfo = new WeatherData();
        // var timeZoneInfo = new TimeZone();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(apiResponse);
        rootNode = rootNode.get(mappingConfig.getTag());
        for (var item : mappingConfig.getMapping()) {
            String value = rootNode.findPath(item.getTagName()).asText();
            setPropertyValue(weatherInfo, item.getPropertyName(), value);
        }
        return weatherInfo;
    }

    public static TimeZone getLocationInfo(String apiResponse) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(apiResponse).getAsJsonObject();

        String timezone = jsonObject.get("timezone").getAsString();
        String timezoneAbbreviation = jsonObject.get("timezone_abbreviation").getAsString();

        return new TimeZone(timezone, timezoneAbbreviation);
    }

    public static void compareCurrentWeatherToHourly(String url, WeatherData data, WeatherConfig config) throws Exception {
        HttpResponse<String> apiUrl = NewsApiReader.getResponse(URI.create(url));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(apiUrl.body());
        JsonNode hourlyNode = rootNode.path("hourly");

        // Extract hourly data as arrays
        JsonNode timeNode = hourlyNode.path("time");
        JsonNode temperatureNode = hourlyNode.path("temperature_2m");

        String[] hourlyTimes = objectMapper.treeToValue(timeNode, String[].class);
        String[] hourlyTemperatures = objectMapper.treeToValue(temperatureNode, String[].class);

        // Compare current weather data with hourly data
        String currentTime = data.getTime();
        String currentTemperature = data.getTemperature();

        for (int i = 0; i < hourlyTimes.length; i++) {
            String hourlyTime = hourlyTimes[i];
            String hourlyTemperature = hourlyTemperatures[i];

            if (currentTime.equals(hourlyTime) && currentTemperature.equals(hourlyTemperature)) {
                System.out.println("Current weather matches hourly data at index " + i);
                return;
            }
        }
    }



    //TODO: Compare 2 strings "time (current)" + "time (hourly)" --> done
    //TODO: forecast: Then add 4 next hours --> 1 set include 1 current hour + 4 next hours


}





