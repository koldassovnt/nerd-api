package kz.nkoldassov.nerdapi.register;

import kz.nkoldassov.nerdapi.client.dto.*;

public interface AuthRegister {
    SignupResponse login(LoginRequest loginRequest);

    MessageResponse register(SignupRequest signUpRequest);

    TokenRefreshResponse refreshToken(RefreshTokenRequest request);
}
