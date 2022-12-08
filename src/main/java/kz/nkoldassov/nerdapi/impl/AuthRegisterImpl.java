package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.client.dto.*;
import kz.nkoldassov.nerdapi.register.AuthRegister;
import org.springframework.stereotype.Component;

@Component
public class AuthRegisterImpl implements AuthRegister {//todo nurlan end up + test
    @Override
    public SignupResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public MessageResponse register(SignupRequest signUpRequest) {
        return null;
    }

    @Override
    public TokenRefreshResponse refreshToken(RefreshTokenRequest request) {
        return null;
    }
}
