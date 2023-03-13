package kz.nkoldassov.nerdapi.impl;

import kz.nkoldassov.nerdapi.db.jdbs.LordOfTheRingsRepository;
import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsCharacter;
import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsMovie;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.LordOfTheRingsInService;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.LordOfTheRingsCharacterDetailResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.LordOfTheRingsCharacterResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.LordOfTheRingsMovieDetailResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.LordOfTheRingsMovieResponse;
import kz.nkoldassov.nerdapi.logging.LOG;
import kz.nkoldassov.nerdapi.register.LordOfTheRingsInServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LordOfTheRingsInServiceRegisterImpl implements LordOfTheRingsInServiceRegister {

    private final LOG log = LOG.forClass(getClass());

    @Autowired
    private LordOfTheRingsInService lordOfTheRingsInService;

    @Autowired
    private LordOfTheRingsRepository lordOfTheRingsRepository;

    @Override
    public List<LordOfTheRingsMovie> getMovies() {

        LordOfTheRingsMovieResponse movies = lordOfTheRingsInService.getMovies();

        List<LordOfTheRingsMovie> resultMovies = new ArrayList<>();

        for (LordOfTheRingsMovieDetailResponse movie : movies.docs) {
            LordOfTheRingsMovie resMovie = new LordOfTheRingsMovie();
            resMovie.externalId = movie.id;
            resMovie.name = movie.name;
            resMovie.runtimeInMinutes = movie.runtimeInMinutes;
            resMovie.budgetInMillions = movie.budgetInMillions;
            resMovie.boxOfficeRevenueInMillions = movie.boxOfficeRevenueInMillions;
            resMovie.academyAwardNominations = movie.academyAwardNominations;
            resMovie.rottenTomatoesScore = movie.rottenTomatoesScore;

            resultMovies.add(resMovie);
        }

        return resultMovies;
    }

    @Override
    public List<LordOfTheRingsCharacter> getCharacters() {

        LordOfTheRingsCharacterResponse characters = lordOfTheRingsInService.getCharacters();

        List<LordOfTheRingsCharacter> resultCharacters = new ArrayList<>();

        for (LordOfTheRingsCharacterDetailResponse character : characters.docs) {
            LordOfTheRingsCharacter resCharacter = new LordOfTheRingsCharacter();
            resCharacter.externalId = character.id;
            resCharacter.height = character.height;
            resCharacter.race = character.race;
            resCharacter.gender = character.gender;
            resCharacter.birth = character.birth;
            resCharacter.spouse = character.spouse;
            resCharacter.death = character.death;
            resCharacter.realm = character.realm;
            resCharacter.hair = character.hair;
            resCharacter.name = character.name;

            resultCharacters.add(resCharacter);
        }

        return resultCharacters;
    }

    @Override
    public void migrateMovies() {
        log.info(() -> "ysJGjJBg44 :: migrate Lord Of The Rings movies start");
        long time1 = System.currentTimeMillis();

        List<LordOfTheRingsMovie> movies = getMovies();
        lordOfTheRingsRepository.saveMovies(movies);

        long time2 = System.currentTimeMillis();
        log.info(() -> "15DZ6p9IV1 :: migrate Lord Of The Rings movies finish in " + (time2 - time1) + "ms");
    }

    @Override
    public void migrateCharacters() {
        log.info(() -> "Y88TsYp45T :: migrate Lord Of The Rings characters start");
        long time1 = System.currentTimeMillis();

        List<LordOfTheRingsCharacter> characters = getCharacters();
        lordOfTheRingsRepository.saveCharacters(characters);

        long time2 = System.currentTimeMillis();
        log.info(() -> "7429zxITs9 :: migrate Lord Of The Rings characters finish in " + (time2 - time1) + "ms");
    }
}
