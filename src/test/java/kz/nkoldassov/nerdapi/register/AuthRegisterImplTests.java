package kz.nkoldassov.nerdapi.register;

import kz.greetgo.util.RND;
import kz.nkoldassov.nerdapi.client.dto.LoginRequest;
import kz.nkoldassov.nerdapi.client.dto.SignupResponse;
import kz.nkoldassov.nerdapi.dao_for_tests.ClientTestDao;
import kz.nkoldassov.nerdapi.impl.AuthRegisterImpl;
import kz.nkoldassov.nerdapi.util.ParentTestNg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthRegisterImplTests extends ParentTestNg {

    @Autowired
    private AuthRegisterImpl authRegister;

    @Autowired
    private ClientTestDao clientTestDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test(expectedExceptions = AuthenticationException.class)
    public void login__NoClient() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.email = RND.strEng(10) + "@mail.com";
        loginRequest.password = RND.strEng(15);

        //
        //
        authRegister.login(loginRequest);
        //
        //
    }

    @Test
    public void login() {
        String email = RND.strEng(10) + "@mail.com";
        String password = RND.strEng(15);

        clientTestDao.insertClientEmailPassword(email, passwordEncoder.encode(password));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.email = email;
        loginRequest.password = password;

        //
        //
        SignupResponse signupResponse = authRegister.login(loginRequest);
        //
        //

        Long lastClientId = clientTestDao.getLastClientId();

        assertThat(signupResponse).isNotNull();
        assertThat(signupResponse.email).isEqualTo(email);
        assertThat(signupResponse.type).isEqualTo("Bearer");
        assertThat(signupResponse.token).isNotNull();
        assertThat(signupResponse.refreshToken).isNotNull();
        assertThat(signupResponse.client).isEqualTo(lastClientId);
    }
}
