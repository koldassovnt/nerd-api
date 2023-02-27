package kz.nkoldassov.nerdapi.beans.factory;

import kz.greetgo.conf.hot.FileConfigFactory;
import kz.nkoldassov.nerdapi.configs.FactsApiConfig;
import kz.nkoldassov.nerdapi.configs.JwtSecurityConfig;
import kz.nkoldassov.nerdapi.configs.LordOfTheRingsApiConfig;
import kz.nkoldassov.nerdapi.configs.SendEmailConfig;
import kz.nkoldassov.nerdapi.util.App;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class AllConfigFactory extends FileConfigFactory {
    @Override
    protected Path getBaseDir() {
        return App.confDir();
    }

    @Bean
    public FactsApiConfig createFactsApiConfig() {
        return createConfig(FactsApiConfig.class);
    }

    @Bean
    public JwtSecurityConfig createSecurityConfig() {
        return createConfig(JwtSecurityConfig.class);
    }

    @Bean
    public SendEmailConfig createSendEmailConfig() {
        return createConfig(SendEmailConfig.class);
    }

    @Bean
    public LordOfTheRingsApiConfig createLordOfTheRingsApiConfig() {
        return createConfig(LordOfTheRingsApiConfig.class);
    }
}
