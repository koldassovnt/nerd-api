package kz.nkoldassov.nerdapi.configs;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Config for service with random facts")
public interface FactsApiConfig {

    @Description("Request endpoint")
    @DefaultStrValue("https://api.api-ninjas.com/v1/facts?limit=")
    String requestUrl();

    @Description("Api key")
    @DefaultStrValue("A5SL+QhMVyb3Qa5sfsyFjA==zRkN6cZiKc5vqhIR")
    String apiKey();

    @Description("Use fake")
    @DefaultBoolValue(true)
    boolean useFake();
}
