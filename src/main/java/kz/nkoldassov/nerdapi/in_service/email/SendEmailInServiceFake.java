package kz.nkoldassov.nerdapi.in_service.email;

import kz.nkoldassov.nerdapi.in_service.email.model.SendEmailRequest;

public class SendEmailInServiceFake implements SendEmailInService {

    @Override
    public void sendEmail(SendEmailRequest emailRequest) {
        System.out.println("mY4xf3PKY2 :: emailRequest = " + emailRequest);
    }
}
