package kz.nkoldassov.nerdapi.register;

import kz.nkoldassov.nerdapi.impl.AuthRegisterImpl;
import kz.nkoldassov.nerdapi.util.ParentTestNg;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class AuthRegisterImplTests extends ParentTestNg {

    @Autowired
    private AuthRegisterImpl authRegister;

    @Test
    public void login() {

    }
}
