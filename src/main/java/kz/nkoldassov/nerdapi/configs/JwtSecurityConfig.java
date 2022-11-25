package kz.nkoldassov.nerdapi.configs;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultLongValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Config for JWT settings")
public interface JwtSecurityConfig {

    @Description("JWT secret code")
    @DefaultStrValue("6V46f76gOZk8S2FTcP0I")
    String jwtSecret();

    @Description("JWT token expiration time in ms")
    @DefaultLongValue(3600000)
    long jwtExpirationMs();

    @Description("JWT refresh token time in ms")
    @DefaultLongValue(86400000)
    long jwtRefreshExpirationMs();

    @Description("Use for test")
    @DefaultBoolValue(true)
    boolean forTest();

    @Description("JWT token expiration time in ms for testing")
    @DefaultLongValue(60000)
    long jwtExpirationMsForTest();

    @Description("JWT refresh token time in ms for testing")
    @DefaultLongValue(120000)
    long jwtRefreshExpirationMsForTest();
}
