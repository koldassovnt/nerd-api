package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.db.model.*;
import kz.nkoldassov.nerdapi.register.LordOfTheRingsServiceRegister;
import kz.nkoldassov.nerdapi.register.RandomFactRegister;
import kz.nkoldassov.nerdapi.register.StarWarsServiceRegister;
import kz.nkoldassov.nerdapi.register.SuperHeroServiceRegister;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TestController {

    @Autowired
    private RandomFactRegister randomFactRegister;

    @Autowired
    private LordOfTheRingsServiceRegister lordOfTheRingsServiceRegister;

    @Autowired
    private StarWarsServiceRegister starWarsServiceRegister;

    @Autowired
    private SuperHeroServiceRegister superHeroServiceRegister;

    @GetMapping("/test/hello")
    public String hello() {
        return "hello at " + new Date() + "!";
    }

    @SneakyThrows
    @GetMapping("/nerd/api/fact")
    public String fact() {
        return randomFactRegister.getRandomFact();
    }

    @GetMapping("/nerd/api/lord-of-the-rings/movies")
    public String lordOfTheRingsMovies() {
        List<LordOfTheRingsMovie> movies = lordOfTheRingsServiceRegister.getMovies();
        return String.valueOf(movies);
    }

    @GetMapping("/nerd/api/lord-of-the-rings/characters")
    public String lordOfTheRingsCharacters() {
        List<LordOfTheRingsCharacter> characters = lordOfTheRingsServiceRegister.getCharacters();
        return String.valueOf(characters);
    }

    @GetMapping("/nerd/api/star-wars/films")
    public String starWarsFilms() {
        List<StarWarsFilm> films = starWarsServiceRegister.getFilms(1);
        return String.valueOf(films);
    }

    @GetMapping("/nerd/api/star-wars/people")
    public String starWarsPeople() {
        List<StarWarsPerson> people = starWarsServiceRegister.getPeople(1);
        return String.valueOf(people);
    }

    @GetMapping("/nerd/api/star-wars/planets")
    public String starWarsPlanets() {
        List<StarWarsPlanet> planets = starWarsServiceRegister.getPlanets(1);
        return String.valueOf(planets);
    }

    @GetMapping("/nerd/api/super-heroes")
    public String superHeroes() {
        List<SuperHeroOrVillain> heroes = superHeroServiceRegister.getHeroes();
        return String.valueOf(heroes);
    }

    @GetMapping("/nerd/api/super-villains")
    public String superVillains() {
        List<SuperHeroOrVillain> villains = superHeroServiceRegister.getVillains();
        return String.valueOf(villains);
    }
}
