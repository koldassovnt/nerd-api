package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StarWarsPlanet {
    public Long id;
    public String externalId;
    public String name;
    public String diameter;
    public String climate;
    public String terrain;
    public String population;

    public List<StarWarsPerson> residents = new ArrayList<>();
    public List<StarWarsFilm> films = new ArrayList<>();
}
