package kz.nkoldassov.nerdapi.in_service.star_wars.model.people;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StarWarsPersonDetailResponse {
    public String name;
    public String height;
    public String mass;
    @JsonProperty("hair_color")
    public String hairColor;
    @JsonProperty("skin_color")
    public String skinColor;
    @JsonProperty("eye_color")
    public String eyeColor;
    @JsonProperty("birth_year")
    public String birthYear;
    public String gender;
    @JsonProperty("homeworld")
    public String homeWorld;
    public List<String> films = new ArrayList<>();
    public List<String> species = new ArrayList<>();
    public List<String> vehicles = new ArrayList<>();
    public List<String> starships = new ArrayList<>();
    public String created;
    public String edited;
    public String url;
}
