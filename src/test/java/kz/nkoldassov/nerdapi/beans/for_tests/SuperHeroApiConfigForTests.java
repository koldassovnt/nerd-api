package kz.nkoldassov.nerdapi.beans.for_tests;

import kz.nkoldassov.nerdapi.configs.SuperHeroApiConfig;
import org.springframework.stereotype.Component;

@Component
public class SuperHeroApiConfigForTests implements SuperHeroApiConfig {

    @Override
    public boolean useFake() {
        return true;
    }

    @Override
    public String apiKey() {
        return "7dkdNCkXnDJ1uA6T1ZRC";
    }

    @Override
    public String apiHost() {
        return "host";
    }

    @Override
    public String heroesEndpoint() {
        return "https://Ye2jNgiwYamyOxQ7pH7M";
    }

    @Override
    public String villainsEndpoint() {
        return "https://4HREmf0gVDpO0Y51tdYe";
    }
}
