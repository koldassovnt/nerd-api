package kz.nkoldassov.nerdapi.db.jdbs;

import kz.nkoldassov.nerdapi.db.model.StarWarsFilm;
import kz.nkoldassov.nerdapi.db.model.StarWarsPerson;
import kz.nkoldassov.nerdapi.db.model.StarWarsPlanet;

import java.util.List;

public interface StarWarsRepository {

    void saveFilms(List<StarWarsFilm> films);

    void savePeople(List<StarWarsPerson> people);

    void savePlanets(List<StarWarsPlanet> planets);
}
