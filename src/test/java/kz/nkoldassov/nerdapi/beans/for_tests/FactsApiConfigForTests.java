package kz.nkoldassov.nerdapi.beans.for_tests;

import kz.nkoldassov.nerdapi.configs.FactsApiConfig;
import org.springframework.stereotype.Component;

@Component
public class FactsApiConfigForTests implements FactsApiConfig {

    @Override
    public String requestUrl() {
        return "testUrl";
    }

    @Override
    public String apiKey() {
        return "111";
    }

    @Override
    public boolean useFake() {
        return true;
    }
}
