
import Configuration.*;
import Configuration.Weather.WeatherConfig;
import Model.Articles;
import Model.TopNews;
import Service.BingNewsService;
import Service.NewsApiReader;
import org.junit.jupiter.api.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static Service.BingNewsService.getTopNews;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    public void testGetItemsFromRssUrl() throws ParserConfigurationException, IOException, SAXException {
        String path = "https://vnexpress.net/rss/tin-moi-nhat.rss";
        NodeList list = BingNewsService.getItemsFromRssUrl(path);
        assertNotNull(list);
        assertTrue(list.getLength() > 0);
    }

    @Test
    public void testGetAllArticles() throws Exception {
        String file = ".\\src\\main\\resources\\BingNewsConfig.json";
        var config = BingNewsService.readConfig(file, BingNewsConfig.class);
        String mappingFile = ".\\src\\main\\resources\\MappingConfig.json";
        var mappingConfig = BingNewsService.readConfig(mappingFile, MappingConfig.class);

        List<Articles> listArticles = BingNewsService.getAllArticles(config, mappingConfig);

        assertFalse(listArticles.isEmpty());
        assertNotNull(listArticles);

    }

    @org.junit.jupiter.api.Test
    public void testGetAllTopNews() throws Exception {
        String topNewsFile = "src\\main\\resources\\TopNewsConfig.json";
        var config = BingNewsService.readConfig(topNewsFile, TopNewsConfig.class);
        List<TopNews> newsSet = getTopNews(config);
        assertNotNull(newsSet);
    }

    @org.junit.jupiter.api.Test
    public void testFetchJsonFromUrl() throws IOException, InterruptedException, URISyntaxException {
        String path = "https://newsdata.io/api/1/news?country=jp&apikey=pub_28863cb92c18d36e7fe58deaaa84e76250636";
        URI uri = new URI(path);
        var item = NewsApiReader.getResponse(uri);
        //var listATC = NewsApiReader.parseJson(item.body());
        assertNotNull(item);
        System.out.println(item.body());
    }

    @org.junit.jupiter.api.Test
    void getAllAdTopic() {
    }

    @Test
    public void testGetLocationInfo() throws Exception {
        //read weather config
        String weatherFile = "src\\main\\resources\\WeatherConfig.json";
        var weatherConfig = BingNewsService.readConfig(weatherFile, WeatherConfig.class);
        var defautCfg = weatherConfig.getDefaultParameters();
        // latitude + longitude --> change
        var apiURL = BingNewsService.changeLocation(10.762622F, 106.660172F, defautCfg);
        var weatherAPI = NewsApiReader.getResponse(URI.create(apiURL));
        var locationInfo = BingNewsService.getLocationInfo(weatherAPI.body());
        assertEquals("Asia/Ho_Chi_Minh", locationInfo.getTimezone());
    }


}