package kz.nkoldassov.nerdapi.beans.factory;

import kz.nkoldassov.nerdapi.configs.FactsApiConfig;
import kz.nkoldassov.nerdapi.in_service.random_fact.RandomFactInService;
import kz.nkoldassov.nerdapi.in_service.random_fact.RandomFactInServiceFake;
import kz.nkoldassov.nerdapi.in_service.random_fact.RandomFactInServiceReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RandomFactInServiceFactory {

    @Autowired
    private FactsApiConfig factsApiConfig;

    @Bean
    public RandomFactInService createRandomFactInService() {
        if (factsApiConfig.useFake()) {
            return new RandomFactInServiceFake();
        }

        return new RandomFactInServiceReal(factsApiConfig.requestUrl(), factsApiConfig.apiKey());
    }
}
