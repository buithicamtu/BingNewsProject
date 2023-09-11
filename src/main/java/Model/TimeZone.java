package Model;

public class TimeZone {
    private String timezone;
    private String timezoneAbbreviation;

    public TimeZone(String timezone, String timezoneAbbreviation) {
        this.timezone = timezone;
        this.timezoneAbbreviation = timezoneAbbreviation;
    }

    public TimeZone(){

    }

//    public TimeZone(String timezone, String timezoneAbbreviation) {
//    }

    public String getTimezone() {
        return timezone;
    }

    public String getTimezoneAbbreviation() {
        return timezoneAbbreviation;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimezoneAbbreviation(String timezoneAbbreviation) {
        this.timezoneAbbreviation = timezoneAbbreviation;
    }

}

