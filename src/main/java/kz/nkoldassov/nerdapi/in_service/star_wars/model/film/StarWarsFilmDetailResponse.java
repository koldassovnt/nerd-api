package kz.nkoldassov.nerdapi.in_service.star_wars.model.film;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StarWarsFilmDetailResponse {
    public String title;
    @JsonProperty("episode_id")
    public Integer episodeId;
    @JsonProperty("opening_crawl")
    public String openingCrawl;
    public String director;
    public String producer;
    @JsonProperty("release_date")
    public String releaseDate;
    public List<String> characters = new ArrayList<>();
    public List<String> planets = new ArrayList<>();
    public List<String> starships = new ArrayList<>();
    public List<String> vehicles = new ArrayList<>();
    public List<String> species = new ArrayList<>();
    public String created;
    public String edited;
    public String url;
}
