package dz.web.api.algeriacitiesdetails.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Params{
    @JsonProperty("Fajr")
    public int fajr;
    @JsonProperty("Isha") 
    public int isha;
}
