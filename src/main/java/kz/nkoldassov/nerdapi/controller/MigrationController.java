package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.register.LordOfTheRingsInServiceRegister;
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
}
