package kz.nkoldassov.nerdapi.configs.init;

import kz.nkoldassov.nerdapi.configs.DbConfig;
import org.springframework.stereotype.Component;

@Component
public class DbConfigInit implements DbConfig {
    @Override
    public String dbName() {
        return "nerd";
    }

    @Override
    public String host() {
        return "localhost";
    }

    @Override
    public int port() {
        return 20018;
    }

    @Override
    public String username() {
        return "nerd";
    }

    @Override
    public String password() {
        return "BS8MJL4s0H5xi2VOSoqT0cMrEy6Ils";
    }
}
