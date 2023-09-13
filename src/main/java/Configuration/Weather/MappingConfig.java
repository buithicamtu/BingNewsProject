package Configuration.Weather;

import Configuration.PropertyMapping;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MappingConfig {
    private String tag;
    private List<PropertyMapping> mapping;

    @JsonProperty("tag")
    public String getTag() { return tag; }
    @JsonProperty("tag")
    public void setTag(String value) { this.tag = value; }

    @JsonProperty("mapping")
    public List<PropertyMapping> getMapping() { return mapping; }
    @JsonProperty("mapping")
    public void setMapping(List<PropertyMapping> value) { this.mapping = value; }
}
