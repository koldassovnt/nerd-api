package kz.nkoldassov.nerdapi.in_service.email;

import kz.nkoldassov.nerdapi.in_service.email.model.SendEmailRequest;

public class SendEmailInServiceFake implements SendEmailInService {

    @Override
    public void sendVerificationEmail(SendEmailRequest emailRequest) {
        System.out.println(emailRequest);
    }
}
