package Service;

import Configuration.BingNewsConfig;
import Configuration.Channel;
import Configuration.MappingConfig;
import Configuration.PropertyMapping;
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
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

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

    public static List<Articles> getAllArticles(BingNewsConfig bingNewsConfig, MappingConfig propertyMapConfig) throws Exception {
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
                Articles atc = mapNodeItem(nodeList.item(i), channel, Articles.class);
                listArticle.add(atc);
            }

        }
        return listArticle;
    }

//    public static <T> T mapNodeItem(Node node, Channel channel, Class<T> classTarget) throws Exception {
//        T instance = classTarget.newInstance();
//        if (node.getNodeType() == Node.ELEMENT_NODE) {
//            Element itemElement = (Element) node;
//            for (var propertyMap : channel.getPropertyMappings()) {
//                String propertyName = propertyMap.getPropertyName();
//                String value = itemElement.getElementsByTagName(propertyMap.getTagName()).item(0).getTextContent();
//                setPropertyValue(instance, propertyName, value);
//            }
//        }
//        return instance;
//    }

    public static <T> T mapNodeItem(Node item, Channel channel, Class<T> classTarget) throws Exception {
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

    public static void setPropertyValue(Object obj, String fieldName, Object value) throws Exception, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

}

