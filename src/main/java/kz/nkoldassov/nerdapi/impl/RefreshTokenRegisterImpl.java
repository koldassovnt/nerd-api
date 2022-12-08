package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.client.dto.RefreshTokenRequest;
import kz.nkoldassov.nerdapi.configs.JwtSecurityConfig;
import kz.nkoldassov.nerdapi.db.jdbs.ClientRepository;
import kz.nkoldassov.nerdapi.db.jdbs.RefreshTokenRepository;
import kz.nkoldassov.nerdapi.db.model.Client;
import kz.nkoldassov.nerdapi.db.model.RefreshToken;
import kz.nkoldassov.nerdapi.exception.TokenRefreshException;
import kz.nkoldassov.nerdapi.register.RefreshTokenRegister;
import kz.nkoldassov.nerdapi.util.DateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.UUID;

@Component
public class RefreshTokenRegisterImpl implements RefreshTokenRegister {// todo nurlan need test

    @Autowired
    private JwtSecurityConfig jwtSecurityConfig;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public RefreshToken getByToken(RefreshTokenRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "V38iDZ0YGv :: request model was null");
        }

        if (Strings.isBlank(request.refreshToken)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "M4H20AJC8b :: refreshToken was blank");
        }

        return refreshTokenRepository.getByToken(request.refreshToken);
    }

    @Override
    public RefreshToken createRefreshToken(Long clientId) {
        if (clientId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "5V3c9H4RAK :: clientId was null");
        }

        Client client = clientRepository.getClient(clientId);

        if (client == null) {
            throw new RuntimeException("1kodCw1nEB :: no client by id = " + clientId);
        }

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.actual = true;
        refreshToken.clientId = clientId;
        refreshToken.expiryDate = DateUtil.addMillisecond(
                new Date(), jwtSecurityConfig.jwtRefreshExpirationMs());
        refreshToken.token = UUID.randomUUID().toString();

        refreshTokenRepository.saveRefreshToken(refreshToken);

        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken == null) {
            throw new RuntimeException("w5F0Tx3UiL :: refreshToken was null");
        }

        if (refreshToken.expiryDate == null) {
            throw new RuntimeException("o7393N7SM5 :: refreshToken expiryDate was null");
        }

        if (refreshToken.expiryDate.compareTo(new Date()) > 0) {
            int deleted = refreshTokenRepository.deleteByClient(refreshToken.clientId);

            if (deleted != 1) {
                throw new RuntimeException("zNKHJxoBii :: cannot delete refreshToken by clientId = " + refreshToken.clientId);
            }

            throw new TokenRefreshException(refreshToken.token, "Refresh token was expired. Please make a new sign in request");
        }

        return refreshToken;
    }

    @Override
    public int deleteRefreshTokenByClient(Long clientId) {
        if (clientId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ey64cVZLaD :: clientId was null");
        }

        return refreshTokenRepository.deleteByClient(clientId);
    }
}
