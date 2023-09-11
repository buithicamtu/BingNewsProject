package Configuration.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultParameters {
    private String hourly;
    private String pastDays;
    private String forecastDays;

    @JsonProperty("hourly")
    public String getHourly() { return hourly; }
    @JsonProperty("hourly")
    public void setHourly(String value) { this.hourly = value; }

    @JsonProperty("past_days")
    public String getPastDays() { return pastDays; }
    @JsonProperty("past_days")
    public void setPastDays(String value) { this.pastDays = value; }

    @JsonProperty("forecast_days")
    public String getForecastDays() { return forecastDays; }
    @JsonProperty("forecast_days")
    public void setForecastDays(String value) { this.forecastDays = value; }
}
