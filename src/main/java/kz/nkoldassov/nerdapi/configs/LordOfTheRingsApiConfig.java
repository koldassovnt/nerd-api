package kz.nkoldassov.nerdapi.configs;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Configs to call Lord of the rings API")
public interface LordOfTheRingsApiConfig {

    @Description("Access Token")
    @DefaultStrValue("el84oViDE32cu4q34mG8")
    String accessToken();

    @Description("Use fake")
    @DefaultBoolValue(true)
    boolean useFake();

    @Description("Endpoint to fetch movies")
    @DefaultStrValue("https://the-one-api.dev/v2/movie")
    String movieEndpoint();

    @Description("Endpoint to fetch characters")
    @DefaultStrValue("https://the-one-api.dev/v2/character")
    String characterEndpoint();
}
