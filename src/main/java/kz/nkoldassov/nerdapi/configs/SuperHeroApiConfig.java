package kz.nkoldassov.nerdapi.configs;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Configs to call Superhero API")
public interface SuperHeroApiConfig {

    @Description("Use fake")
    @DefaultBoolValue(true)
    boolean useFake();

    @Description("X-RapidAPI-Key value")
    @DefaultStrValue("YOUR_API_KEY")
    String apiKey();

    @Description("X-RapidAPI-Host value")
    @DefaultStrValue("superhero-search.p.rapidapi.com")
    String apiHost();

    @Description("Endpoint to fetch heroes")
    @DefaultStrValue("https://superhero-search.p.rapidapi.com/api/heroes")
    String heroesEndpoint();

    @Description("Endpoint to fetch villains")
    @DefaultStrValue("https://superhero-search.p.rapidapi.com/api/villains")
    String villainsEndpoint();
}
