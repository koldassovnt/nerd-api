package kz.nkoldassov.nerdapi.beans.all;

import kz.nkoldassov.nerdapi.db.impl.*;
import kz.nkoldassov.nerdapi.db.jdbs.*;
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

    @Bean
    SuperHeroOrVillainRepository createSuperHeroOrVillainRepository() {
        return new SuperHeroOrVillainRepositoryImpl(jdbcTemplate);
    }
}
