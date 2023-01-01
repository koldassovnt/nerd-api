package kz.nkoldassov.nerdapi.beans.all;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.nkoldassov.nerdapi.configs.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfiguration {

    @Autowired
    private DbConfig dbConfig;

    @Bean
    public DataSource dataSource() {

        var config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(dbConfig.url());
        config.setUsername(dbConfig.username());
        config.setPassword(dbConfig.password());

        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate applicationDataConnection(){
        return new JdbcTemplate(dataSource());
    }
}
