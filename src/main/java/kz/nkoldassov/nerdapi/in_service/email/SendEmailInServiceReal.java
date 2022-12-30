package kz.nkoldassov.nerdapi.in_service.email;

import kz.nkoldassov.nerdapi.configs.SendEmailConfig;
import kz.nkoldassov.nerdapi.in_service.email.model.SendEmailRequest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class SendEmailInServiceReal implements SendEmailInService {

    private final JavaMailSenderImpl javaMailSender;
    public SendEmailInServiceReal(SendEmailConfig sendEmailConfig) {
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
        //todo nurlan end up
    }
}
