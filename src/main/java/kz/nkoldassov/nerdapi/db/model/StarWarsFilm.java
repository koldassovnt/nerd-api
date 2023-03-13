package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StarWarsFilm {
    public Long id;
    public String externalId;
    public String title;
    public String director;
    public String producer;
    public String releaseDate;

    public List<StarWarsPerson> characters = new ArrayList<>();
    public List<StarWarsPlanet> planets = new ArrayList<>();

    public static StarWarsFilm of(String externalId) {
        StarWarsFilm film = new StarWarsFilm();
        film.externalId = externalId;
        return film;
    }
}
