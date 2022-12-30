package kz.nkoldassov.nerdapi.beans.for_tests;

import kz.nkoldassov.nerdapi.configs.SendEmailConfig;
import org.springframework.stereotype.Component;

@Component
public class SendEmailConfigForTests implements SendEmailConfig {

    @Override
    public boolean useFake() {
        return true;
    }

    @Override
    public String host() {
        return "smtp.gmail.com";
    }

    @Override
    public int port() {
        return 587;
    }

    @Override
    public String username() {
        return "noreply@nerd-api.kz";
    }

    @Override
    public String password() {
        return "5pfFPM79n3MyGNqBB3s6";
    }

    @Override
    public boolean smtpAuth() {
        return true;
    }

    @Override
    public boolean smtpStartTlsEnable() {
        return true;
    }

    @Override
    public boolean smtpSslEnable() {
        return true;
    }

    @Override
    public boolean mimeCharset() {
        return true;
    }
}
