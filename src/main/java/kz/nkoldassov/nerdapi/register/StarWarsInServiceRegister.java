package kz.nkoldassov.nerdapi.register;

import kz.nkoldassov.nerdapi.db.model.StarWarsFilm;
import kz.nkoldassov.nerdapi.db.model.StarWarsPerson;
import kz.nkoldassov.nerdapi.db.model.StarWarsPlanet;

import java.util.List;

public interface StarWarsInServiceRegister {

    List<StarWarsFilm> getFilms(int page);

    List<StarWarsPerson> getPeople(int page);

    List<StarWarsPlanet> getPlanets(int page);

    void migrateFilms();

    void migratePeople();

    void migratePlanets();
}
