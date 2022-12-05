package kz.nkoldassov.nerdapi.beans.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import kz.nkoldassov.nerdapi.configs.JwtSecurityConfig;
import kz.nkoldassov.nerdapi.logging.LOG;
import kz.nkoldassov.nerdapi.logging.LogStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final LOG log = LOG.byName(LogStatic.SECURITY_LOG);

    @Autowired
    private JwtSecurityConfig jwtSecurityConfig;

    public String generateJwtToken(UserDetailsImpl userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtSecurityConfig.jwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, jwtSecurityConfig.jwtSecret())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecurityConfig.jwtSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecurityConfig.jwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }
}
