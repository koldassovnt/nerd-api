package kz.nkoldassov.nerdapi.register;

import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsCharacter;
import kz.nkoldassov.nerdapi.db.model.LordOfTheRingsMovie;

import java.util.List;

public interface LordOfTheRingsServiceRegister {

    List<LordOfTheRingsMovie> getMovies();

    List<LordOfTheRingsCharacter> getCharacters();

    void migrateMovies();

    void migrateCharacters();
}
