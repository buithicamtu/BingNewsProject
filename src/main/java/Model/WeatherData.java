package Model;

public class WeatherData {
    private String temperature;
    private String currentTime;
    private String time;
    private String temperature_2m;
    private String precipitation;

    public WeatherData(){

    }
    public WeatherData(String temperature, String currentTime) {
        this.temperature = temperature;
        this.currentTime = currentTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(String temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

}
