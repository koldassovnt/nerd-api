package kz.nkoldassov.nerdapi.controller;

import kz.nkoldassov.nerdapi.client.dto.*;
import kz.nkoldassov.nerdapi.register.AuthRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRegister authRegister;

    @PostMapping("/login")
    public ResponseEntity<SignupResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        SignupResponse signupResponse = authRegister.login(loginRequest);
        return ResponseEntity.ok(signupResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody SignupRequest signUpRequest,
                                                    HttpServletRequest request) {
        MessageResponse messageResponse = authRegister.register(signUpRequest, getSiteURL(request));
        return ResponseEntity.ok(messageResponse);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        TokenRefreshResponse response = authRegister.refreshToken(request);
        return ResponseEntity.ok(response);
    }
}
