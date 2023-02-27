package kz.nkoldassov.nerdapi.in_service;

import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.LordOfTheRingsInService;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.LordOfTheRingsCharacterResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.Doc;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.LordOfTheRingsMovieResponse;
import org.springframework.stereotype.Component;


@Component
public class LordOfTheRingsInServiceForTests implements LordOfTheRingsInService {

    @Override
    public LordOfTheRingsMovieResponse getMovies() {
        Doc movie = new Doc();
        movie.id = "77z3COM63hKi4Xkf2a0h";
        movie.name = "Test movie";

        LordOfTheRingsMovieResponse response = new LordOfTheRingsMovieResponse();
        response.docs.add(movie);

        return response;
    }

    @Override
    public LordOfTheRingsCharacterResponse getCharacters() {
        kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.Doc character =
                new kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.Doc();

        character.id = "pAaYza2huIUip4o08RIo";
        character.name = "Test character";

        LordOfTheRingsCharacterResponse response = new LordOfTheRingsCharacterResponse();
        response.docs.add(character);

        return response;
    }
}
