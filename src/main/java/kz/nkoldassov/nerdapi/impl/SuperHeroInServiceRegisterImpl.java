package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.db.jdbs.SuperHeroOrVillainRepository;
import kz.nkoldassov.nerdapi.db.model.SuperHeroOrVillain;
import kz.nkoldassov.nerdapi.in_service.superhero.SuperHeroInService;
import kz.nkoldassov.nerdapi.in_service.superhero.model.SuperHeroInServiceResponse;
import kz.nkoldassov.nerdapi.logging.LOG;
import kz.nkoldassov.nerdapi.register.SuperHeroInServiceRegister;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuperHeroInServiceRegisterImpl implements SuperHeroInServiceRegister {

    private final LOG log = LOG.forClass(getClass());

    @Autowired
    private SuperHeroInService superHeroInService;

    @Autowired
    private SuperHeroOrVillainRepository superHeroOrVillainRepository;

    @Override
    public List<SuperHeroOrVillain> getHeroes() {
        List<SuperHeroInServiceResponse> heroes = superHeroInService.getHeroes();

        return mapInServiceModelToDbModel(heroes);
    }

    @Override
    public List<SuperHeroOrVillain> getVillains() {
        List<SuperHeroInServiceResponse> villains = superHeroInService.getVillains();

        return mapInServiceModelToDbModel(villains);
    }

    @Override
    public void migrateHeroes() {
        log.info(() -> "PU1qX8D5Wj :: Superheroes migration start");
        long time1 = System.currentTimeMillis();

        List<SuperHeroOrVillain> heroes = getHeroes();

        superHeroOrVillainRepository.saveAsList(heroes);

        long time2 = System.currentTimeMillis();
        log.info(() -> "6211GB0qKM :: Superheroes migration finish in " + (time2 - time1) + "ms");
    }

    @Override
    public void migrateVillains() {
        log.info(() -> "r9cH2Htj93 :: Supervillains migration start");
        long time1 = System.currentTimeMillis();

        List<SuperHeroOrVillain> villains = getVillains();

        superHeroOrVillainRepository.saveAsList(villains);

        long time2 = System.currentTimeMillis();
        log.info(() -> "O16XOvKpK0 :: Supervillains migration finish in " + (time2 - time1) + "ms");
    }

    @NotNull
    private List<SuperHeroOrVillain> mapInServiceModelToDbModel(List<SuperHeroInServiceResponse> villains) {
        List<SuperHeroOrVillain> villainList = new ArrayList<>();

        for (SuperHeroInServiceResponse villain : villains) {
            SuperHeroOrVillain ret = new SuperHeroOrVillain();
            ret.externalId = villain.id;
            ret.name = villain.name;
            ret.slug = villain.slug;

            if (villain.biography != null) {
                ret.fullName = villain.biography.fullName;
                ret.alterEgos = villain.biography.alterEgos;

                if (villain.biography.aliases != null &&
                        !villain.biography.aliases.isEmpty()) {
                    ret.aliases = String.join(", ", villain.biography.aliases);
                }

                ret.firstAppearance = villain.biography.firstAppearance;
                ret.publisher = villain.biography.publisher;
                ret.alignment = villain.biography.alignment;
            }

            if (villain.images != null) {
                ret.imageUrl = villain.images.lg;
            }

            villainList.add(ret);
        }

        return villainList;
    }


}
