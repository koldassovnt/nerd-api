package kz.nkoldassov.nerdapi.controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/email")
public class EmailController {

    //todo nurlan approve email

    @GetMapping("/verify")
    public String verifyClientEmail(@RequestParam("code") String code) {
        System.out.println("w6iz5TN080 :: code = " + code);
        return "Successfully verified!";
    }
}
