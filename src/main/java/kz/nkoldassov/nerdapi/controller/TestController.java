package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.db.model.*;
import kz.nkoldassov.nerdapi.in_service.superhero.SuperHeroInService;
import kz.nkoldassov.nerdapi.in_service.superhero.model.SuperHeroInServiceResponse;
import kz.nkoldassov.nerdapi.register.LordOfTheRingsInServiceRegister;
import kz.nkoldassov.nerdapi.register.RandomFactRegister;
import kz.nkoldassov.nerdapi.register.StarWarsInServiceRegister;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RandomFactRegister randomFactRegister;

    @Autowired
    private LordOfTheRingsInServiceRegister lordOfTheRingsInServiceRegister;

    @Autowired
    private StarWarsInServiceRegister starWarsInServiceRegister;

    @Autowired
    private SuperHeroInService superHeroInService; //todo nurlan потом изменить на вызов регистра

    @GetMapping("/hello")
    public String hello() {
        return "hello at " + new Date() + "!";
    }

    @SneakyThrows
    @GetMapping("/api/fact")
    public String fact() {
        return randomFactRegister.getRandomFact();
    }

    @GetMapping("/api/lord-of-the-rings/movies")
    public String lordOfTheRingsMovies() {
        List<LordOfTheRingsMovie> movies = lordOfTheRingsInServiceRegister.getMovies();
        return String.valueOf(movies);
    }

    @GetMapping("/api/lord-of-the-rings/characters")
    public String lordOfTheRingsCharacters() {
        List<LordOfTheRingsCharacter> characters = lordOfTheRingsInServiceRegister.getCharacters();
        return String.valueOf(characters);
    }

    @GetMapping("/api/star-wars/films")
    public String starWarsFilms() {
        List<StarWarsFilm> films = starWarsInServiceRegister.getFilms(1);
        return String.valueOf(films);
    }

    @GetMapping("/api/star-wars/people")
    public String starWarsPeople() {
        List<StarWarsPerson> people = starWarsInServiceRegister.getPeople(1);
        return String.valueOf(people);
    }

    @GetMapping("/api/star-wars/planets")
    public String starWarsPlanets() {
        List<StarWarsPlanet> planets = starWarsInServiceRegister.getPlanets(1);
        return String.valueOf(planets);
    }

    @GetMapping("/api/super-heroes")
    public String superHeroes() {
        List<SuperHeroInServiceResponse> heroes = superHeroInService.getHeroes();
        return String.valueOf(heroes);
    }

    @GetMapping("/api/super-villains")
    public String superVillains() {
        List<SuperHeroInServiceResponse> villains = superHeroInService.getVillains();
        return String.valueOf(villains);
    }
}
