package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StarWarsPerson {
    public Long id;
    public String externalId;
    public String name;
    public String height;
    public String mass;
    public String birthYear;

    public StarWarsPlanet homeWorld;

    public List<StarWarsFilm> films = new ArrayList<>();

    public static StarWarsPerson of(String externalId) {
        StarWarsPerson ret = new StarWarsPerson();
        ret.externalId = externalId;
        return ret;
    }
}
