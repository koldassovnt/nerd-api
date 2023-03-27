package kz.nkoldassov.nerdapi.in_service;

import kz.nkoldassov.nerdapi.in_service.superhero.SuperHeroInService;
import kz.nkoldassov.nerdapi.in_service.superhero.model.SuperHeroInServiceResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuperHeroInServiceForTests implements SuperHeroInService {
    @Override
    public List<SuperHeroInServiceResponse> getHeroes() {
        return new ArrayList<>();
    }

    @Override
    public List<SuperHeroInServiceResponse> getVillains() {
        return new ArrayList<>();
    }
}
