package Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// TopNewsAPI.java
public class TopNewsAPI {
    private String name;
    private String uri;
    private List<PropertyMapping> mapping;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("uri")
    public String getURI() { return uri; }
    @JsonProperty("uri")
    public void setURI(String value) { this.uri = value; }

    @JsonProperty("mapping")
    public List<PropertyMapping> getMapping() { return mapping; }
    @JsonProperty("mapping")
    public void setMapping(List<PropertyMapping> value) { this.mapping = value; }
}
