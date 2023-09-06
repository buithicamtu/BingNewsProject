package Configuration;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

public class APIConfig {
    private List<TopNewsAPI> topNewsAPI;

    @JsonProperty("TopNewsAPI")
    public List<TopNewsAPI> getTopNewsAPI() {
        return topNewsAPI;
    }

    @JsonProperty("TopNewsAPI")
    public void setTopNewsAPI(List<TopNewsAPI> value) {
        this.topNewsAPI = value;
    }
}

