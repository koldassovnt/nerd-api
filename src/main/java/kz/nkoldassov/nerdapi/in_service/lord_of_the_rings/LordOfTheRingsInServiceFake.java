package kz.nkoldassov.nerdapi.in_service.lord_of_the_rings;

import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.LordOfTheRingsCharacterResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.Doc;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.LordOfTheRingsMovieResponse;

public class LordOfTheRingsInServiceFake implements LordOfTheRingsInService {

    @Override
    public LordOfTheRingsMovieResponse getMovies() {
        Doc movie = new Doc();
        movie.id = "2izImYePmx6ohQzLIht6";
        movie.name = "Fake movie";

        LordOfTheRingsMovieResponse response = new LordOfTheRingsMovieResponse();
        response.docs.add(movie);

        return response;
    }

    @Override
    public LordOfTheRingsCharacterResponse getCharacters() {
        kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.Doc character =
                new kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.Doc();

        character.id = "P9GcAc7Qw482RBgVm3oM";
        character.name = "Fake character";

        LordOfTheRingsCharacterResponse response = new LordOfTheRingsCharacterResponse();
        response.docs.add(character);

        return response;
    }
}
