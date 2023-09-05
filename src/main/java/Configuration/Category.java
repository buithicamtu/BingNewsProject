package Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Category {
    private String cateTitle;
    private List<Channel> channels;

    @JsonProperty("Cate_Title")
    public String getCateTitle() { return cateTitle; }
    @JsonProperty("Cate_Title")
    public void setCateTitle(String value) { this.cateTitle = value; }

    @JsonProperty("channels")
    public List<Channel> getChannels() { return channels; }
    @JsonProperty("channels")
    public void setChannels(List<Channel> value) { this.channels = value; }
}
