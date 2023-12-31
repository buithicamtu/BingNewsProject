package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Articles {

    public String title;
    private String description;
    private String link;
    private String guid;
    private String pubDate;
    private String category;
    private String author;

    public Articles() {
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.title = value;
    }
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.description = value;
    }

    @JsonProperty("link")
    public String getURL() {
        return link;
    }

    @JsonProperty("link")
    public void setURL(String value) {
        this.link = value;
    }

    @JsonProperty("guid")
    public String getGUID() {
        return guid;
    }

    @JsonProperty("guid")
    public void setGUID(String value) {
        this.guid = value;
    }

    @JsonProperty("pubDate")
    public String getPubDate() {
        return pubDate;
    }

    @JsonProperty("pubDate")
    public void setPubDate(String value) {
        this.pubDate = value;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String value) {
        this.category = value;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String value) {
        this.author = value;
    }

}
