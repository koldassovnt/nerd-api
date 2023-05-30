package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.register.LordOfTheRingsServiceRegister;
import kz.nkoldassov.nerdapi.register.RandomFactRegister;
import kz.nkoldassov.nerdapi.register.StarWarsServiceRegister;
import kz.nkoldassov.nerdapi.register.SuperHeroServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nerd")
public class NerdApiController {

    @Autowired
    private StarWarsServiceRegister starWarsService;

    @Autowired
    private LordOfTheRingsServiceRegister lordOfTheRingsService;

    @Autowired
    private SuperHeroServiceRegister superHeroServiceRegister;

    @Autowired
    private RandomFactRegister randomFactRegister;

    //todo nurlan here is gonna be all apis for client
}
