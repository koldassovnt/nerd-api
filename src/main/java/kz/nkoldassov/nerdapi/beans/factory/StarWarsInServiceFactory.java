package kz.nkoldassov.nerdapi.beans.factory;

import kz.nkoldassov.nerdapi.configs.StarWarsApiConfig;
import kz.nkoldassov.nerdapi.in_service.star_wars.StarWarsInService;
import kz.nkoldassov.nerdapi.in_service.star_wars.StarWarsInServiceFake;
import kz.nkoldassov.nerdapi.in_service.star_wars.StarWarsInServiceReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class StarWarsInServiceFactory {

    @Autowired
    private StarWarsApiConfig starWarsApiConfig;

    @Bean
    public StarWarsInService createStarWarsInService() {
        if (starWarsApiConfig.useFake()) {
            return new StarWarsInServiceFake();
        }

        return new StarWarsInServiceReal(starWarsApiConfig);
    }

}
