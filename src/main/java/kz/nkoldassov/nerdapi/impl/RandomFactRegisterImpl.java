package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.in_service.random_fact.RandomFactInService;
import kz.nkoldassov.nerdapi.register.RandomFactRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kz.nkoldassov.nerdapi.util.ServiceCallStatic.RESPONSE_ERROR_TEXT;

@Component
public class RandomFactRegisterImpl implements RandomFactRegister {

    @Autowired
    private RandomFactInService randomFactInService;

    @Override
    public String getRandomFact() {
        String randomFact = randomFactInService.getRandomFact();

        if (RESPONSE_ERROR_TEXT.equals(randomFact)) {
            throw new RuntimeException("8uPA7bn1XE :: Service call error!");
        }

        return randomFact;
    }
}
