package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.LordOfTheRingsInService;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.LordOfTheRingsCharacterResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.LordOfTheRingsMovieResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.StarWarsInService;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.film.StarWarsFilmsResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.people.StarWarsPeopleResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.planet.StarWarsPlanetResponse;
import kz.nkoldassov.nerdapi.register.RandomFactRegister;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RandomFactRegister randomFactRegister;

    @Autowired
    private LordOfTheRingsInService lordOfTheRingsInService;//todo nurlan потом вынести в регистр

    @Autowired
    private StarWarsInService starWarsInService;//todo nurlan потом вынести в регистр

    @GetMapping("/hello")
    public String hello() {
        return "hello at " + new Date() + "!";
    }

    @SneakyThrows
    @GetMapping("/fact")
    public String fact() {
        return randomFactRegister.getRandomFact();
    }

    @GetMapping("/lord-of-the-rings/movies")
    public String lordOfTheRingsMovies() {
        LordOfTheRingsMovieResponse movies = lordOfTheRingsInService.getMovies();
        return movies.toString();
    }

    @GetMapping("/lord-of-the-rings/characters")
    public String lordOfTheRingsCharacters() {
        LordOfTheRingsCharacterResponse characters = lordOfTheRingsInService.getCharacters();
        return characters.toString();
    }

    @GetMapping("/star-wars/films")
    public String starWarsFilms() {
        StarWarsFilmsResponse films = starWarsInService.getFilms(1);
        return films.toString();
    }

    @GetMapping("/star-wars/people")
    public String starWarsPeople() {
        StarWarsPeopleResponse people = starWarsInService.getPeople(1);
        return people.toString();
    }

    @GetMapping("/star-wars/planet")
    public String starWarsPlanet() {
        StarWarsPlanetResponse planets = starWarsInService.getPlanets(1);
        return planets.toString();
    }
}
