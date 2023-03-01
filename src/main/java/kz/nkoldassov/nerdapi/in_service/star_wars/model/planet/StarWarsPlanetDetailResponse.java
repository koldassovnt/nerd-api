package kz.nkoldassov.nerdapi.in_service.star_wars.model.planet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StarWarsPlanetDetailResponse {
    public String name;
    @JsonProperty("rotation_period")
    public String rotationPeriod;
    @JsonProperty("orbital_period")
    public String orbitalPeriod;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    @JsonProperty("surface_water")
    public String surfaceWater;
    public String population;
    public List<String> residents = new ArrayList<>();
    public List<String> films = new ArrayList<>();
    public String created;
    public String edited;
    public String url;
}
