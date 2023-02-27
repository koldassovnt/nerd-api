package kz.nkoldassov.nerdapi.beans.factory;

import kz.nkoldassov.nerdapi.configs.LordOfTheRingsApiConfig;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.LordOfTheRingsInService;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.LordOfTheRingsInServiceFake;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.LordOfTheRingsInServiceReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LordOfTheRingsInServiceFactory {

    @Autowired
    private LordOfTheRingsApiConfig lordOfTheRingsApiConfig;

    @Bean
    public LordOfTheRingsInService createLordOfTheRingsInService() {
        if (lordOfTheRingsApiConfig.useFake()) {
            return new LordOfTheRingsInServiceFake();
        }

        return new LordOfTheRingsInServiceReal(lordOfTheRingsApiConfig);
    }
}
