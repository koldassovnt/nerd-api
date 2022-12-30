package kz.nkoldassov.nerdapi.configs;

import kz.greetgo.conf.hot.DefaultBoolValue;
import kz.greetgo.conf.hot.DefaultIntValue;
import kz.greetgo.conf.hot.DefaultStrValue;
import kz.greetgo.conf.hot.Description;

@Description("Config for service to send emails")
public interface SendEmailConfig {

    @Description("Use fake")
    @DefaultBoolValue(true)
    boolean useFake();

    @Description("SMTP host")
    @DefaultStrValue("smtp.gmail.com")
    String host();

    @Description("SMTP Port")
    @DefaultIntValue(587)
    int port();

    @Description("Mail sender username")
    @DefaultStrValue("noreply@nerd-api.kz")
    String username();

    @Description("Mail sender password")
    @DefaultStrValue("5pfFPM79n3MyGNqBB3s6")
    String password();

    @Description("Value for mail.smtp.auth")
    @DefaultBoolValue(true)
    boolean smtpAuth();

    @Description("Value for mail.smtp.starttls.enable")
    @DefaultBoolValue(true)
    boolean smtpStartTlsEnable();

    @Description("Value for mail.smtp.ssl.enable")
    @DefaultBoolValue(true)
    boolean smtpSslEnable();

    @Description("Value for mail.mime.charset")
    @DefaultBoolValue(true)
    boolean mimeCharset();
}
