package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.client.dto.MessageResponse;
import kz.nkoldassov.nerdapi.register.AuthRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private AuthRegister authRegister;

    @GetMapping("/verify")
    public ResponseEntity<MessageResponse> verifyClientEmail(@RequestParam("code") String code) {

        authRegister.approveEmailByVerificationCode(code);

        return ResponseEntity.ok(MessageResponse.of("Successfully verified!"));
    }
}
