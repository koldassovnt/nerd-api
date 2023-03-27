package kz.nkoldassov.nerdapi.in_service.superhero;

import kz.nkoldassov.nerdapi.in_service.superhero.model.SuperHeroInServiceResponse;

import java.util.ArrayList;
import java.util.List;

public class SuperHeroInServiceFake implements SuperHeroInService {
    @Override
    public List<SuperHeroInServiceResponse> getHeroes() {
        return new ArrayList<>();
    }

    @Override
    public List<SuperHeroInServiceResponse> getVillains() {
        return new ArrayList<>();
    }
}
