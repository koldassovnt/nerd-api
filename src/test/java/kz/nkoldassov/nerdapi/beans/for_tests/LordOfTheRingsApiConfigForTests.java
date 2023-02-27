package kz.nkoldassov.nerdapi.beans.for_tests;

import kz.nkoldassov.nerdapi.configs.LordOfTheRingsApiConfig;
import org.springframework.stereotype.Component;

@Component
public class LordOfTheRingsApiConfigForTests implements LordOfTheRingsApiConfig {
    @Override
    public String accessToken() {
        return "HJcDd0qKEdQt0SxZ7IB4";
    }

    @Override
    public boolean useFake() {
        return true;
    }

    @Override
    public String movieEndpoint() {
        return "https://5nUI8cSg4YVIW70xkB73";
    }

    @Override
    public String characterEndpoint() {
        return "https://1MT7QoDyz9c65jxBWGtm";
    }
}
