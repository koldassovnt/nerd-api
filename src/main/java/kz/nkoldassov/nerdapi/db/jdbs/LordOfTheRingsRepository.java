package kz.nkoldassov.nerdapi.db.jdbs;

import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsCharacter;
import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsMovie;

import java.util.List;

public interface LordOfTheRingsRepository {

    void saveCharacters(List<LordOfTheRingsCharacter> characters);

    void saveMovies(List<LordOfTheRingsMovie> movies);
}
