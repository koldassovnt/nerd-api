package kz.nkoldassov.nerdapi.in_service.star_wars;

import kz.nkoldassov.nerdapi.in_service.star_wars.model.film.StarWarsFilmsResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.people.StarWarsPeopleResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.planet.StarWarsPlanetResponse;

public interface StarWarsInService {

    StarWarsFilmsResponse getFilms(int page);

    StarWarsPeopleResponse getPeople(int page);

    StarWarsPlanetResponse getPlanets(int page);
}
