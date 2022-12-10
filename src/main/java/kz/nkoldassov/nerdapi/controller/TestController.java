package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.register.RandomFactRegister;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RandomFactRegister randomFactRegister;

    @GetMapping("/hello")
    public String hello() {
        return "hello at " + new Date() + "!";
    }

    @SneakyThrows
    @GetMapping("/fact")
    public String fact() {
        return randomFactRegister.getRandomFact();
    }
}
