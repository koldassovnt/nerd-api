package kz.nkoldassov.nerdapi.in_service.lord_of_the_rings;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.nkoldassov.nerdapi.configs.LordOfTheRingsApiConfig;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character.LordOfTheRingsCharacterResponse;
import kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie.LordOfTheRingsMovieResponse;
import kz.nkoldassov.nerdapi.logging.LOG;
import lombok.SneakyThrows;
import okhttp3.*;

public class LordOfTheRingsInServiceReal implements LordOfTheRingsInService {

    private static final LOG log = LOG.forClass(LordOfTheRingsInServiceReal.class);

    private final String movieEndpoint;
    private final String characterEndpoint;
    private final String token;

    OkHttpClient client = new OkHttpClient();

    public LordOfTheRingsInServiceReal(LordOfTheRingsApiConfig apiConfig) {
        movieEndpoint = apiConfig.movieEndpoint();
        characterEndpoint = apiConfig.characterEndpoint();
        token = apiConfig.accessToken();
    }

    @SneakyThrows
    @Override
    public LordOfTheRingsMovieResponse getMovies() {
        log.info(() -> "22W1hey0UY :: getMovies start");

        Request request = new Request.Builder()
                .url(movieEndpoint)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        log.info(() -> "ebO2PF3Ck6 :: request = " + request);

        LordOfTheRingsMovieResponse movieResponse;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new RuntimeException("6mCyDp40T5 :: responseBody occurred to be null");
            }

            String responseStr = responseBody.string();

            log.info(() -> "d3JOHjFju8 :: response = " + responseStr);

            ObjectMapper mapper = new ObjectMapper();
            movieResponse = mapper.readValue(responseStr, LordOfTheRingsMovieResponse.class);

            LordOfTheRingsMovieResponse finalMovieResponse = movieResponse;
            log.info(() -> "zKpXD5j6Pg :: movieResponse = " + finalMovieResponse);
        }

        return movieResponse;
    }

    @SneakyThrows
    @Override
    public LordOfTheRingsCharacterResponse getCharacters() {
        log.info(() -> "qgX8lk1j6s :: getCharacters start");

        Request request = new Request.Builder()
                .url(characterEndpoint)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        log.info(() -> "aoiZmb83l4 :: request = " + request);

        LordOfTheRingsCharacterResponse characterResponse;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new RuntimeException("wL8q4CGf8W :: responseBody occurred to be null");
            }

            String responseStr = responseBody.string();

            log.info(() -> "Q7f7kPJc5C :: response = " + responseStr);

            ObjectMapper mapper = new ObjectMapper();
            characterResponse = mapper.readValue(responseStr, LordOfTheRingsCharacterResponse.class);

            LordOfTheRingsCharacterResponse finalCharacters = characterResponse;
            log.info(() -> "6hKJ6Zb2C3 :: characterResponse = " + finalCharacters);
        }

        return characterResponse;
    }
}
