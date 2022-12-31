package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.beans.security.JwtUtils;
import kz.nkoldassov.nerdapi.beans.security.UserDetailsImpl;
import kz.nkoldassov.nerdapi.client.dto.*;
import kz.nkoldassov.nerdapi.db.jdbs.ClientRepository;
import kz.nkoldassov.nerdapi.db.model.Client;
import kz.nkoldassov.nerdapi.db.model.RefreshToken;
import kz.nkoldassov.nerdapi.in_service.email.SendEmailInService;
import kz.nkoldassov.nerdapi.in_service.email.model.SendEmailRequest;
import kz.nkoldassov.nerdapi.register.AuthRegister;
import kz.nkoldassov.nerdapi.register.RefreshTokenRegister;
import kz.nkoldassov.nerdapi.util.RandomStrUtil;
import org.apache.logging.log4j.util.Strings;
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

    @Autowired
    private SendEmailInService sendEmailInService;

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
    public MessageResponse register(SignupRequest signUpRequest, String siteUrl) {
        if (signUpRequest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "1xpaNr0mUu :: signUpRequest is null");
        }

        if (clientRepository.existsByEmail(signUpRequest.email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "YTWzJdg64z :: email is already taken");
        }

        String verificationCode = RandomStrUtil.generateStr(64);

        Client client = Client.forRegister(signUpRequest.email,
                passwordEncoder.encode(signUpRequest.password),
                signUpRequest.name,
                signUpRequest.surname,
                verificationCode);

        clientRepository.saveClientForRegister(client);

        sendEmailInService.sendVerificationEmail(getSendEmailRequest(client, siteUrl));

        return MessageResponse.of("vjXMGpHIJ8 :: successful registration!");
    }

    private SendEmailRequest getSendEmailRequest(Client client, String siteUrl) {
        SendEmailRequest emailRequest = new SendEmailRequest();
        emailRequest.receiverEmail = client.email;
        emailRequest.subject = "Please verify your registration";

        String verifyUrl = siteUrl + "/email/verify?code=" + client.verificationCode;

        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "NERD API.";
        content = content.replace("[[name]]", client.fio());
        content = content.replace("[[URL]]", verifyUrl);

        emailRequest.content = content;

        return emailRequest;
    }

    @Override
    public TokenRefreshResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenRegister.getByToken(request);

        refreshTokenRegister.verifyExpiration(refreshToken);

        Client client = clientRepository.getClient(refreshToken.clientId);

        String token = jwtUtils.generateTokenFromUsername(client.email);

        return TokenRefreshResponse.of(token, refreshToken.token);
    }

    @Override
    public void approveEmailByVerificationCode(String code) {
        if (Strings.isBlank(code)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ZwMfDwd7be :: verification code is blank");
        }

        Client client = clientRepository.getClientByVerificationCode(code);

        if (client == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "8J4kx1NuUw :: no client by verification code = " + code);
        }

        clientRepository.approveClientEmail(client.client);
    }
}
