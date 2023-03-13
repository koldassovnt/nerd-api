package kz.nkoldassov.nerdapi.beans.all;

import kz.nkoldassov.nerdapi.db.impl.ClientRepositoryImpl;
import kz.nkoldassov.nerdapi.db.impl.LordOfTheRingsRepositoryImpl;
import kz.nkoldassov.nerdapi.db.impl.RefreshTokenRepositoryImpl;
import kz.nkoldassov.nerdapi.db.impl.StarWarsRepositoryImpl;
import kz.nkoldassov.nerdapi.db.jdbs.ClientRepository;
import kz.nkoldassov.nerdapi.db.jdbs.LordOfTheRingsRepository;
import kz.nkoldassov.nerdapi.db.jdbs.RefreshTokenRepository;
import kz.nkoldassov.nerdapi.db.jdbs.StarWarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbsConfiguration {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    ClientRepository createClientRepository() {
        return new ClientRepositoryImpl(jdbcTemplate);
    }

    @Bean
    RefreshTokenRepository createRefreshTokenRepository() {
        return new RefreshTokenRepositoryImpl(jdbcTemplate);
    }

    @Bean
    LordOfTheRingsRepository createLordOfTheRingsRepository() {
        return new LordOfTheRingsRepositoryImpl(jdbcTemplate);
    }

    @Bean
    StarWarsRepository createStarWarsRepository() {
        return new StarWarsRepositoryImpl(jdbcTemplate);
    }
}
