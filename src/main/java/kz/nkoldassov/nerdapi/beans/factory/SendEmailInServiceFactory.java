package kz.nkoldassov.nerdapi.beans.factory;

import kz.nkoldassov.nerdapi.configs.SendEmailConfig;
import kz.nkoldassov.nerdapi.in_service.email.SendEmailInService;
import kz.nkoldassov.nerdapi.in_service.email.SendEmailInServiceFake;
import kz.nkoldassov.nerdapi.in_service.email.SendEmailInServiceReal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SendEmailInServiceFactory {

    @Autowired
    private SendEmailConfig sendEmailConfig;

    @Bean
    public SendEmailInService createSendEmailInService() {
        if (sendEmailConfig.useFake()) {
            return new SendEmailInServiceFake();
        }

        return new SendEmailInServiceReal(sendEmailConfig);
    }
}
