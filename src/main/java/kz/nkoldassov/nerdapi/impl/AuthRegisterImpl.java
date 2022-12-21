package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.beans.security.JwtUtils;
import kz.nkoldassov.nerdapi.beans.security.UserDetailsImpl;
import kz.nkoldassov.nerdapi.client.dto.*;
import kz.nkoldassov.nerdapi.db.jdbs.ClientRepository;
import kz.nkoldassov.nerdapi.db.model.Client;
import kz.nkoldassov.nerdapi.db.model.RefreshToken;
import kz.nkoldassov.nerdapi.register.AuthRegister;
import kz.nkoldassov.nerdapi.register.RefreshTokenRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthRegisterImpl implements AuthRegister {

    @Autowired
    private RefreshTokenRegister refreshTokenRegister;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public SignupResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        RefreshToken refreshToken = refreshTokenRegister.createRefreshToken(userDetails.id);

        SignupResponse response = new SignupResponse();
        response.token = jwt;
        response.client = refreshToken.clientId;
        response.email = userDetails.email;
        response.refreshToken = refreshToken.token;

        return response;
    }

    @Override
    public MessageResponse register(SignupRequest signUpRequest) {
        if (signUpRequest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "1xpaNr0mUu :: signUpRequest is null");
        }

        if (clientRepository.existsByEmail(signUpRequest.email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "YTWzJdg64z :: email is already taken");
        }

        Client client = Client.register(signUpRequest.email, passwordEncoder.encode(signUpRequest.password));

        clientRepository.saveClientForRegister(client);

        return MessageResponse.of("vjXMGpHIJ8 :: successful registration!");
    }

    @Override
    public TokenRefreshResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRegister.getByToken(request);

        refreshTokenRegister.verifyExpiration(refreshToken);

        Client client = clientRepository.getClient(refreshToken.clientId);

        String token = jwtUtils.generateTokenFromUsername(client.email);

        return TokenRefreshResponse.of(token, refreshToken.token);
    }
}
