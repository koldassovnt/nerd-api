package kz.nkoldassov.nerdapi.beans.for_tests;

import kz.nkoldassov.nerdapi.configs.StarWarsApiConfig;
import org.springframework.stereotype.Component;

@Component
public class StarWarsApiConfigForTests implements StarWarsApiConfig {

    @Override
    public boolean useFake() {
        return true;
    }

    @Override
    public String filmsEndpoint() {
        return "https://eLF2L2CL8a4G0cu7U1SH";
    }

    @Override
    public String peopleEndpoint() {
        return "https://4bbj4q5DkJzt707WNUIm";
    }

    @Override
    public String planetsEndpoint() {
        return "https://3h7tUcxNSm1mupWF3ybs";
    }
}
