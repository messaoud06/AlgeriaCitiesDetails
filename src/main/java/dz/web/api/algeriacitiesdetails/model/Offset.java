package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Offset{
    @JsonProperty("Imsak")
    public int imsak;
    @JsonProperty("Fajr") 
    public int fajr;
    @JsonProperty("Sunrise") 
    public int sunrise;
    @JsonProperty("Dhuhr") 
    public int dhuhr;
    @JsonProperty("Asr") 
    public int asr;
    @JsonProperty("Maghrib") 
    public int maghrib;
    @JsonProperty("Sunset") 
    public int sunset;
    @JsonProperty("Isha") 
    public int isha;
    @JsonProperty("Midnight") 
    public int midnight;
}
