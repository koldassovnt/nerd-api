package kz.nkoldassov.nerdapi.beans.factory;

import kz.nkoldassov.nerdapi.configs.SuperHeroApiConfig;
import kz.nkoldassov.nerdapi.in_service.superhero.SuperHeroInService;
import kz.nkoldassov.nerdapi.in_service.superhero.SuperHeroInServiceFake;
import kz.nkoldassov.nerdapi.in_service.superhero.SuperHeroInServiceReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SuperHeroInServiceFactory {

    @Autowired
    private SuperHeroApiConfig apiConfig;

    @Bean
    public SuperHeroInService createSuperHeroInService() {
        if (apiConfig.useFake()) {
            return new SuperHeroInServiceFake();
        }

        return new SuperHeroInServiceReal(apiConfig);
    }
}
