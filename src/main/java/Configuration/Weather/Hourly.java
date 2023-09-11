package Configuration.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Hourly {
    private String time;
    private String temperature2M;
    private String precipitationProbability;

    @JsonProperty("time")
    public String getTime() { return time; }
    @JsonProperty("time")
    public void setTime(String value) { this.time = value; }

    @JsonProperty("temperature_2m")
    public String getTemperature2M() { return temperature2M; }
    @JsonProperty("temperature_2m")
    public void setTemperature2M(String value) { this.temperature2M = value; }

    @JsonProperty("precipitation_probability")
    public String getPrecipitationProbability() { return precipitationProbability; }
    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(String value) { this.precipitationProbability = value; }
}
