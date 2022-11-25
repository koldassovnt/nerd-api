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
    public long jwtExpirationMs() {
        return 3600000;
    }

    @Override
    public long jwtRefreshExpirationMs() {
        return 86400000;
    }

    @Override
    public boolean forTest() {
        return true;
    }

    @Override
    public long jwtExpirationMsForTest() {
        return 60000;
    }

    @Override
    public long jwtRefreshExpirationMsForTest() {
        return 120000;
    }
}
