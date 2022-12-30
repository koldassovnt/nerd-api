package kz.nkoldassov.nerdapi.in_service.email;

import kz.nkoldassov.nerdapi.in_service.email.model.SendEmailRequest;

public interface SendEmailInService {

    void sendVerificationEmail(SendEmailRequest emailRequest);
}
