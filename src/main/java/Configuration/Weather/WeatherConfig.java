package Configuration.Weather;
    // WeatherConfig.java

import com.fasterxml.jackson.annotation.*;

public class WeatherConfig {
        private String apiEndpoint;
        private DefaultParameters defaultParameters;
        private String timezone;
        private String timezoneAbbreviation;
        private MappingConfig currentWeatherMappingConfig;
        private MappingConfig hourlyMappingConfig;

        @JsonProperty("api_endpoint")
        public String getAPIEndpoint() { return apiEndpoint; }
        @JsonProperty("api_endpoint")
        public void setAPIEndpoint(String value) { this.apiEndpoint = value; }

        @JsonProperty("default_parameters")
        public DefaultParameters getDefaultParameters() { return defaultParameters; }
        @JsonProperty("default_parameters")
        public void setDefaultParameters(DefaultParameters value) { this.defaultParameters = value; }

        @JsonProperty("timezone")
        public String getTimezone() { return timezone; }
        @JsonProperty("timezone")
        public void setTimezone(String value) { this.timezone = value; }

        @JsonProperty("timezone_abbreviation")
        public String getTimezoneAbbreviation() { return timezoneAbbreviation; }
        @JsonProperty("timezone_abbreviation")
        public void setTimezoneAbbreviation(String value) { this.timezoneAbbreviation = value; }

        @JsonProperty("current_weather_config")
        public MappingConfig getCurrentWeatherConfig() { return currentWeatherMappingConfig; }
        @JsonProperty("current_weather_config")
        public void setCurrentWeatherConfig(MappingConfig value) { this.currentWeatherMappingConfig = value; }

        @JsonProperty("Hourly_Config")
        public MappingConfig getHourlyConfig() { return hourlyMappingConfig; }
        @JsonProperty("Hourly_Config")
        public void setHourlyConfig(MappingConfig value) { this.hourlyMappingConfig = value; }
    }

