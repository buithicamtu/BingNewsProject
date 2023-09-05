package Service;

import Configuration.BingNewsConfig;
import Configuration.Channel;
import Configuration.MappingConfig;
import Model.Articles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static NodeList getItemsFromRssUrl(String url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = (Document) db.parse(new URL(url).openStream());
        NodeList listItems = doc.getElementsByTagName("item");

        return listItems;

    }

    public static List<Articles> getAllArticles(BingNewsConfig bingNewsConfig, MappingConfig propertyMapConfig) throws Exception {

        List<Channel> listChannel = getListChannel(bingNewsConfig);
        List<Articles> listArticle = new ArrayList<>();
        for (var channel : listChannel) {
            NodeList nodeList = getItemsFromRssUrl(channel.getRSSURL());
            for (int i = 0; i <= nodeList.getLength(); i++) {
                channel.setPropertyMappings(propertyMapConfig.getChannels()
                        .stream()
                        .filter(x -> x.getChannelName().equals(channel.getChannelName()))
                        .findFirst()
                        .orElse(null)
                        .getPropertyMappings());
                Articles atc = parseNodeItem(nodeList.item(i), channel, Articles.class);
                listArticle.add(atc);
            }

        }
        return listArticle;
    }

    public static <T> T parseNodeItem(Node node, Channel channel, Class<T> classTarget) throws Exception {
        T instance = classTarget.newInstance();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element itemElement = (Element) node;
            for (var propertyMap : channel.getPropertyMappings()) {
                String propertyName = propertyMap.getPropertyName();
                String value = itemElement.getElementsByTagName(propertyMap.getTagName()).item(0).getTextContent();
                setPropertyValue(instance, propertyName, value);
            }
        }
        return instance;
    }

    public static void setPropertyValue(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

}

