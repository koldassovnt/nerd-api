package kz.nkoldassov.nerdapi.in_service.lord_of_the_rings;

import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.LordOfTheRingsCharacterResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.LordOfTheRingsMovieResponse;


public interface LordOfTheRingsInService {

    LordOfTheRingsMovieResponse getMovies();

    LordOfTheRingsCharacterResponse getCharacters();
}
