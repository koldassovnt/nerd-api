package kz.nkoldassov.nerdapi.register;

import kz.nkoldassov.nerdapi.db.model.SuperHeroOrVillain;

import java.util.List;

public interface SuperHeroInServiceRegister {

    List<SuperHeroOrVillain> getHeroes();

    List<SuperHeroOrVillain> getVillains();

    void migrateHeroes();

    void migrateVillains();
}
