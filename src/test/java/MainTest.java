
import Configuration.BingNewsConfig;
import Configuration.MappingConfig;
import Model.Articles;
import Service.BingNewsService;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    public void getItemsFromRssUrl() throws ParserConfigurationException, IOException, SAXException {
        String path = "https://vnexpress.net/rss/tin-moi-nhat.rss";
        NodeList list = BingNewsService.getItemsFromRssUrl(path);
        assertNotNull(list);
        for (int i = 0; i <= list.getLength(); i++) {
            System.out.println(list.item(i).getTextContent());
        }
    }

    @Test
    public void getAllArticles() throws Exception {
        String bingNewsConfigPath = "src\\main\\resources\\BingNewsConfig.json";
        var bingNewsConfig = BingNewsService.readConfig(bingNewsConfigPath, BingNewsConfig.class);
        String mappingConfigPath = ".\\src\\main\\resources\\MappingConfig.json";
        var mappingConfig = BingNewsService.readConfig(mappingConfigPath, MappingConfig.class);
        List<Articles> listArticles = BingNewsService.getAllArticles(bingNewsConfig, mappingConfig);

        assertTrue(listArticles.size() > 0);
        assertNotNull(listArticles);
    }


    @org.junit.jupiter.api.Test
    void getAllAdTopic() {
    }

    @org.junit.jupiter.api.Test
    void getTopNews() {
    }

    @org.junit.jupiter.api.Test
    void getTrendingNews() {
    }
}