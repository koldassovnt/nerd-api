package kz.nkoldassov.nerdapi.configs;

public interface DbConfig {
    String dbName();

    String host();

    int port();

    String username();

    String password();

    default String url() {
        return "jdbc:postgresql://" + host() + ":" + port() + "/" + dbName();
    }

}
