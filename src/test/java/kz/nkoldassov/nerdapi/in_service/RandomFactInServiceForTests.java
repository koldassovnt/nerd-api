package kz.nkoldassov.nerdapi.in_service;

import kz.greetgo.util.RND;
import kz.nkoldassov.nerdapi.in_service.random_fact.RandomFactInService;
import org.springframework.stereotype.Component;

@Component
public class RandomFactInServiceForTests implements RandomFactInService {

    @Override
    public String getRandomFact() {
        return RND.strEng(10) + " " + RND.strEng(5) + " " + RND.strEng(10) + ".";
    }
}
