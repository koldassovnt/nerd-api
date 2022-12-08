package kz.nkoldassov.nerdapi.beans.for_tests;

import kz.nkoldassov.nerdapi.configs.JwtSecurityConfig;
import org.springframework.stereotype.Component;

@Component
public class JwtSecurityConfigForTests implements JwtSecurityConfig {

    @Override
    public String jwtSecret() {
        return "6V46f76gOZk8S2FTcP0I";
    }

    @Override
    public int jwtExpirationMs() {
        return 3600000;
    }

    @Override
    public int jwtRefreshExpirationMs() {
        return 86400000;
    }

    @Override
    public boolean forTest() {
        return true;
    }

    @Override
    public int jwtExpirationMsForTest() {
        return 60000;
    }

    @Override
    public int jwtRefreshExpirationMsForTest() {
        return 120000;
    }
}
