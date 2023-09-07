package Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MapTopNews {
    private String name;
    private String uri;
    private String result;
    private List<PropertyMapping> mapping;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("uri")
    public String getURI() {
        return uri;
    }

    @JsonProperty("uri")
    public void setURI(String value) {
        this.uri = value;
    }

    @JsonProperty("mapping")
    public List<PropertyMapping> getMapping() {
        return mapping;
    }

    @JsonProperty("mapping")
    public void setMapping(List<PropertyMapping> value) {
        this.mapping = value;
    }

    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(String value) {
        this.result = value;
    }
}
