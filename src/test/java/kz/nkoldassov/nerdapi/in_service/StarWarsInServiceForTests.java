package kz.nkoldassov.nerdapi.in_service;

import kz.nkoldassov.nerdapi.in_service.star_wars.StarWarsInService;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.film.StarWarsFilmsResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.people.StarWarsPeopleResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.planet.StarWarsPlanetResponse;
import org.springframework.stereotype.Component;

@Component
public class StarWarsInServiceForTests implements StarWarsInService {

    @Override
    public StarWarsFilmsResponse getFilms(int page) {
        return new StarWarsFilmsResponse();
    }

    @Override
    public StarWarsPeopleResponse getPeople(int page) {
        return new StarWarsPeopleResponse();
    }

    @Override
    public StarWarsPlanetResponse getPlanets(int page) {
        return new StarWarsPlanetResponse();
    }
}
