package kz.nkoldassov.nerdapi.in_service.email;

import kz.nkoldassov.nerdapi.configs.SendEmailConfig;
import kz.nkoldassov.nerdapi.in_service.email.model.SendEmailRequest;
import kz.nkoldassov.nerdapi.logging.LOG;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class SendEmailInServiceReal implements SendEmailInService {

    private static final LOG log = LOG.forClass(SendEmailInServiceReal.class);

    private final JavaMailSenderImpl javaMailSender;
    public SendEmailInServiceReal(SendEmailConfig sendEmailConfig) {//todo nurlan test real
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(sendEmailConfig.host());
        javaMailSender.setPort(sendEmailConfig.port());
        javaMailSender.setUsername(sendEmailConfig.username());
        javaMailSender.setPassword(sendEmailConfig.password());

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", sendEmailConfig.smtpAuth() + "");
        props.put("mail.smtp.ssl.enable", sendEmailConfig.smtpSslEnable() + "");
        props.put("mail.smtp.starttls.enable", sendEmailConfig.smtpStartTlsEnable() + "");
        props.put("mail.mime.charset", sendEmailConfig.mimeCharset() + "");
    }

    @Override
    public void sendVerificationEmail(SendEmailRequest emailRequest) {

        log.info(() -> "5PGEfSFmVy :: emailRequest = " + emailRequest);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.receiverEmail);
        message.setSubject(emailRequest.subject);
        message.setText(emailRequest.content);

        javaMailSender.send(message);

        log.info(() -> "nkQgDDB7w2 :: email has been send to " + emailRequest.receiverEmail);
    }
}
