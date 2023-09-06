
import Configuration.BingNewsConfig;
import Configuration.MappingConfig;
import Model.Articles;
import Service.BingNewsService;
import Service.NewsApiReader;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    public void getItemsFromRssUrl() throws ParserConfigurationException, IOException, SAXException {
        String path = "https://vnexpress.net/rss/tin-moi-nhat.rss";
        NodeList list = BingNewsService.getItemsFromRssUrl(path);
        assertNotNull(list);
        assertTrue(list.getLength() > 0);

    }
    @Test
    public void getAllArticles() throws Exception {
        String bingNewsConfigPath = ".\\src\\main\\resources\\BingNewsConfig.json";
        BingNewsConfig bingNewsConfig = BingNewsService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = ".\\src\\main\\resources\\MappingConfig.json";
        MappingConfig mappingConfig = BingNewsService.readConfig(mappingConfigPath, MappingConfig.class);

        List<Articles> listArticles = BingNewsService.getAllArticles(bingNewsConfig, mappingConfig);

        assertFalse(listArticles.isEmpty());
        assertNotNull(listArticles);
    }


    @org.junit.jupiter.api.Test
    void getAllAdTopic() {
    }

    @org.junit.jupiter.api.Test
    void getTopNews() throws Exception {
        String topNewsConfgPath = "";
        BingNewsConfig Confg = BingNewsService.readConfig(topNewsConfgPath, BingNewsConfig.class);
        var topNews = BingNewsService.getTopNews(Confg);
        assertNotNull(topNews);
    }

    @org.junit.jupiter.api.Test
    public void fetchJsonFromUrl() throws IOException, InterruptedException, URISyntaxException {
         String path = "https://newsdata.io/api/1/news?country=vi&apikey=pub_28863cb92c18d36e7fe58deaaa84e76250636";
         URI uri = new URI(path);
        var item = NewsApiReader.getResponse(uri);
        assertNotNull(item);
        System.out.println(item.body());
    }
}