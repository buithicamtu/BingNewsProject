package Configuration.Weather;
    // WeatherConfig.java

import Configuration.PropertyMapping;
import com.fasterxml.jackson.annotation.*;

import java.util.List;

public class WeatherConfig {
        private String apiEndpoint;
        private DefaultParameters defaultParameters;
        private String timezone;
        private String timezoneAbbreviation;
        private List<PropertyMapping> mapping;

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

        @JsonProperty("mapping")
        public List<PropertyMapping> getMapping() { return mapping; }
        @JsonProperty("mapping")
        public void setMapping(List<PropertyMapping> value) { this.mapping = value; }
    }

