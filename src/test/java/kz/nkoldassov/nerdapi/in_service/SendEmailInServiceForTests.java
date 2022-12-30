package kz.nkoldassov.nerdapi.in_service;

import kz.nkoldassov.nerdapi.in_service.email.SendEmailInService;
import kz.nkoldassov.nerdapi.in_service.email.model.SendEmailRequest;
import org.springframework.stereotype.Component;

@Component
public class SendEmailInServiceForTests implements SendEmailInService {

    @Override
    public void sendVerificationEmail(SendEmailRequest emailRequest) {
        System.out.println(emailRequest);
    }
}
