package kz.nkoldassov.nerdapi.configs;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Configs to call Star Wars API")
public interface StarWarsApiConfig {

    @Description("Use fake")
    @DefaultBoolValue(true)
    boolean useFake();

    @Description("Endpoint to fetch films")
    @DefaultStrValue("https://swapi.dev/api/films/")
    String filmsEndpoint();

    @Description("Endpoint to fetch 1st page of people")
    @DefaultStrValue("https://swapi.dev/api/people/")
    String peopleEndpoint();

    @Description("Endpoint to fetch 1st page of planet")
    @DefaultStrValue("https://swapi.dev/api/planets/")
    String planetsEndpoint();
}
