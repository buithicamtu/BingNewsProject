package Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MappingConfig {
    private List<Channel> channels;

    @JsonProperty("channels")
    public List<Channel> getChannels() { return channels; }
    @JsonProperty("channels")
    public void setChannels(List<Channel> value) { this.channels = value; }
}


