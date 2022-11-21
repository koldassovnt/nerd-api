package kz.nkoldassov.nerdapi.beans.all;

import kz.nkoldassov.nerdapi.configs.DbConfig;
import kz.nkoldassov.nerdapi.logging.LOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowConfigValuesOnStartup {
    @Autowired
    private DbConfig dbConfig;
    private final LOG log = LOG.forClass(getClass());

    public void show() {

        log.info(() -> "********************************************************************");
        log.info(() -> "****** ");
        log.info(() -> "****** Db configs");
        log.info(() -> "****** url: " + dbConfig.url());
        log.info(() -> "****** username: " + dbConfig.username());
        log.info(() -> "****** password: " + dbConfig.password());
        log.info(() -> "****** ");
        log.info(() -> "********************************************************************");

    }

}
