package Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TopNewsConfig {

    private List<MapTopNews> topNewsConfig;

    @JsonProperty("TopNewsConfig")
    public List<MapTopNews> getTopNewsConfig() { return topNewsConfig; }
    @JsonProperty("TopNewsConfig")
    public void setTopNewsConfig(List<MapTopNews> value) { this.topNewsConfig = value; }
}

