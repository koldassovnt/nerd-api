package kz.nkoldassov.nerdapi.db.jdbs;

import kz.nkoldassov.nerdapi.db.model.SuperHeroOrVillain;

import java.util.List;

public interface SuperHeroOrVillainRepository {
    void saveAsList(List<SuperHeroOrVillain> superHeroOrVillains);
}
