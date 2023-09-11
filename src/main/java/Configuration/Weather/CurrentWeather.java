package Configuration.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;

// CurrentWeather.java
public class CurrentWeather {
    private String temperature;
    private String time;

    @JsonProperty("temperature")
    public String getTemperature() { return temperature; }
    @JsonProperty("temperature")
    public void setTemperature(String value) { this.temperature = value; }

    @JsonProperty("time")
    public String getTime() { return time; }
    @JsonProperty("time")
    public void setTime(String value) { this.time = value; }
}
