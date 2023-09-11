package Configuration.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Unit {
    private String time;
    private String temperature_2M;
    private String precipitationProbability;
    private String precipitation;
    private String rain;

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String value) {
        this.time = value;
    }

    @JsonProperty("temperature_2m")
    public String getTemperature_2M() {
        return temperature_2M;
    }

    @JsonProperty("temperature_2m")
    public void setTemperature_2M(String value) {
        this.temperature_2M = value;
    }

    @JsonProperty("precipitation_probability")
    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(String value) {
        this.precipitationProbability = value;
    }

    @JsonProperty("precipitation")
    public String getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(String value) {
        this.precipitation = value;
    }

    @JsonProperty("rain")
    public String getRain() {
        return rain;
    }

    @JsonProperty("rain")
    public void setRain(String value) {
        this.rain = value;
    }
}
