package kz.nkoldassov.nerdapi.in_service.random_fact;

public class RandomFactInServiceFake implements RandomFactInService {

    @Override
    public String getRandomFact() {
        return "RANDOM FACT!";
    }
}
