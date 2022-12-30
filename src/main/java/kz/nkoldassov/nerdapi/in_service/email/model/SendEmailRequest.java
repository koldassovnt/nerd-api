package kz.nkoldassov.nerdapi.in_service.email.model;

import lombok.ToString;

@ToString
public class SendEmailRequest {
    public String subject;
    public String content;
    public String receiverEmail;
}
