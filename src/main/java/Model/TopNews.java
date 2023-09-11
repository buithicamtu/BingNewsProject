package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopNews extends Articles{
    private String video_url;
    private String article_id;

    public TopNews(){
        super();
    }

    String title = this.getTitle();
    String description = this.getDescription();
    String link = this.getURL();
    String guid = this.getGUID();
    String pubDate = this.getPubDate();
    String category = this.getCategory();
    String creator = this.getAuthor();

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
