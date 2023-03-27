package kz.nkoldassov.nerdapi.in_service.superhero;

import kz.nkoldassov.nerdapi.in_service.superhero.model.SuperHeroInServiceResponse;

import java.util.List;

public interface SuperHeroInService {

    List<SuperHeroInServiceResponse> getHeroes();

    List<SuperHeroInServiceResponse> getVillains();
}
