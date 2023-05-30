package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.register.LordOfTheRingsServiceRegister;
import kz.nkoldassov.nerdapi.register.StarWarsServiceRegister;
import kz.nkoldassov.nerdapi.register.SuperHeroServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nerd/migration")
public class MigrationController {

    @Autowired
    private LordOfTheRingsServiceRegister lordOfTheRingsInService;

    @Autowired
    private StarWarsServiceRegister starWarsServiceRegister;

    @Autowired
    private SuperHeroServiceRegister superHeroServiceRegister;

    @GetMapping("/lord-of-the-rings/movies")
    public String lordOfTheRingsMovies() {
        lordOfTheRingsInService.migrateMovies();
        return "ok";
    }

    @GetMapping("/lord-of-the-rings/characters")
    public String lordOfTheRingsCharacters() {
        lordOfTheRingsInService.migrateCharacters();
        return "ok";
    }

    @GetMapping("/star-wars/films")
    public String starWarsFilms() {
        starWarsServiceRegister.migrateFilms();
        return "ok";
    }

    @GetMapping("/star-wars/people")
    public String starWarsPeople() {
        starWarsServiceRegister.migratePeople();
        return "ok";
    }

    @GetMapping("/star-wars/planets")
    public String starWarsPlanets() {
        starWarsServiceRegister.migratePlanets();
        return "ok";
    }

    @GetMapping("/superhero")
    public String superHero() {
        superHeroServiceRegister.migrateHeroes();
        return "ok";
    }

    @GetMapping("/supervillain")
    public String superVillain() {
        superHeroServiceRegister.migrateVillains();
        return "ok";
    }
}
