package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Timings{
    @JsonProperty("Fajr")
    public String Fajr;
    @JsonProperty("Sunrise")
    public String Sunrise;
    @JsonProperty("Dhuhr")
    public String Dhuhr;
    @JsonProperty("Asr")
    public String Asr;
    @JsonProperty("Sunset")
    public String Sunset;
    @JsonProperty("Maghrib")
    public String Maghrib;
    @JsonProperty("Isha")
    public String Isha;
    @JsonProperty("Imsak") 
    public String Imsak;
    @JsonProperty("Midnight") 
    public String Midnight;
    @JsonProperty("Firstthird") 
    public String Firstthird;
    @JsonProperty("Lastthird") 
    public String Lastthird;
}
