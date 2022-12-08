package kz.nkoldassov.nerdapi.configs;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Config for JWT settings")
public interface JwtSecurityConfig {

    @Description("JWT secret code")
    @DefaultStrValue("6V46f76gOZk8S2FTcP0I")
    String jwtSecret();

    @Description("JWT token expiration time in ms")
    @DefaultIntValue(3600000)
    int jwtExpirationMs();

    @Description("JWT refresh token time in ms")
    @DefaultIntValue(86400000)
    int jwtRefreshExpirationMs();

    @Description("Use for test")
    @DefaultBoolValue(true)
    boolean forTest();

    @Description("JWT token expiration time in ms for testing")
    @DefaultIntValue(60000)
    int jwtExpirationMsForTest();

    @Description("JWT refresh token time in ms for testing")
    @DefaultIntValue(120000)
    int jwtRefreshExpirationMsForTest();
}
