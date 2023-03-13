package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.register.LordOfTheRingsInServiceRegister;
import kz.nkoldassov.nerdapi.register.StarWarsInServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/migration")
public class MigrationController {

    @Autowired
    private LordOfTheRingsInServiceRegister lordOfTheRingsInService;

    @Autowired
    private StarWarsInServiceRegister starWarsInServiceRegister;

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
        starWarsInServiceRegister.migrateFilms();
        return "ok";
    }

    @GetMapping("/star-wars/people")
    public String starWarsPeople() {
        starWarsInServiceRegister.migratePeople();
        return "ok";
    }

    @GetMapping("/star-wars/planets")
    public String starWarsPlanets() {
        starWarsInServiceRegister.migratePlanets();
        return "ok";
    }
}
