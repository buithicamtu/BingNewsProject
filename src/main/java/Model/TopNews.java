package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopNews {
    private String title;
    private String description;
    private String link;
    private String creator;
    private String pubDate;
    private String video_url;
    private String article_id;

    public TopNews(){

    }

    public TopNews(String creator, String title, String description, String link, String pubDate, String video_url, String article_id) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.video_url = video_url;
        this.article_id =article_id;
    }
    public void printOutInfo() {
        System.out.println();
        System.out.println("Creator: " + this.creator);
        System.out.println("Title: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("PubDate: " + this.pubDate);
        System.out.println("Link: " + this.link);
        System.out.println("Video" + this.video_url);
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
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String value) {
        this.link = value;
    }

    @JsonProperty("creator")
    public String getCreator() {
        return creator;
    }

    @JsonProperty("creator")
    public void setCreator(String value) {
        this.creator = value;
    }

    @JsonProperty("pubDate")
    public String getPubDate() {
        return pubDate;
    }

    @JsonProperty("pubDate")
    public void setPubDate(String value) {
        this.pubDate = value;
    }

    @JsonProperty("video_url")
    public String getVideoUrl() {
        return video_url;
    }

    @JsonProperty("category")
    public void setVideoUrl(String value) {
        this.video_url = value;
    }

    @JsonProperty("article_id")
    public String getArticleId() {
        return article_id;
    }

    @JsonProperty("article_id")
    public void setArticleId(String value) {
        this.article_id = value;
    }
}
