package Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Channel {
    private String channelName;
    private String rssURL;
    private List<PropertyMapping> propertyMappings;
    @JsonProperty("propertyMappings")
    public List<PropertyMapping> getPropertyMappings() { return propertyMappings; }
    @JsonProperty("propertyMappings")
    public void setPropertyMappings(List<PropertyMapping> value) { this.propertyMappings = value; }
    @JsonProperty("channelName")
    public String getChannelName() { return channelName; }
    @JsonProperty("channelName")
    public void setChannelName(String value) { this.channelName = value; }
    @JsonProperty("rssUrl")
    public String getRSSURL() { return rssURL; }
    @JsonProperty("rssUrl")
    public void setRSSURL(String value) { this.rssURL = value; }
}
