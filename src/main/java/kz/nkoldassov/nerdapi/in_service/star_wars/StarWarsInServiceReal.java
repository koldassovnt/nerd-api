package kz.nkoldassov.nerdapi.in_service.star_wars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import kz.nkoldassov.nerdapi.configs.StarWarsApiConfig;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.film.StarWarsFilmsResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.people.StarWarsPeopleResponse;
import kz.nkoldassov.nerdapi.in_service.star_wars.model.planet.StarWarsPlanetResponse;
import kz.nkoldassov.nerdapi.logging.LOG;
import lombok.SneakyThrows;
import okhttp3.*;

public class StarWarsInServiceReal implements StarWarsInService {

    private static final LOG log = LOG.forClass(StarWarsInServiceReal.class);

    private final String filmsEndpoint;
    private final String peopleEndpoint;
    private final String planetsEndpoint;

    OkHttpClient client = new OkHttpClient();

    public StarWarsInServiceReal(StarWarsApiConfig apiConfig) {
        filmsEndpoint = apiConfig.filmsEndpoint();
        peopleEndpoint = apiConfig.peopleEndpoint();
        planetsEndpoint = apiConfig.planetsEndpoint();
    }

    @SneakyThrows
    @Override
    public StarWarsFilmsResponse getFilms(int page) {
        log.info(() -> "YGVEjXw5Ad :: getFilms for page = " + page + " start");

        if (page < 1) {
            throw new RuntimeException("l0cPvHqXMU :: page cannot be less than 1");
        }

        String finalUrl = filmsEndpoint + "?page=" + page;

        Request request = new Request.Builder()
                .url(finalUrl)
                .addHeader("accept", "application/json")
                .build();

        log.info(() -> "iT66OIP479 :: request = " + request);

        StarWarsFilmsResponse filmsResponse;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new RuntimeException("2N8NKr1Aar :: responseBody occurred to be null");
            }

            String responseStr = responseBody.string();

            log.info(() -> "EX3zkCds66 :: response = " + responseStr);

            ObjectMapper mapper = new ObjectMapper();
            try {
                filmsResponse = mapper.readValue(responseStr, StarWarsFilmsResponse.class);
            } catch (UnrecognizedPropertyException ex) {
                log.info(() -> "J0qGzt2Y0Z :: catch exception = " + ex.getMessage());
                filmsResponse = new StarWarsFilmsResponse();
            }

            StarWarsFilmsResponse finalFilmsResponse = filmsResponse;
            log.info(() -> "F1ohHKsoGy :: filmsResponse = " + finalFilmsResponse);
        }

        return filmsResponse;
    }

    @SneakyThrows
    @Override
    public StarWarsPeopleResponse getPeople(int page) {
        log.info(() -> "k3bdMmBG1S :: getPeople for page = " + page + " start");

        if (page < 1) {
            throw new RuntimeException("9ZA255T32J :: page cannot be less than 1");
        }

        String finalUrl = peopleEndpoint + "?page=" + page;

        Request request = new Request.Builder()
                .url(finalUrl)
                .addHeader("accept", "application/json")
                .build();

        log.info(() -> "lZI4Q8oJIQ :: request = " + request);

        StarWarsPeopleResponse peopleResponse;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new RuntimeException("7q1B51UlNo :: responseBody occurred to be null");
            }

            String responseStr = responseBody.string();

            log.info(() -> "216n68DQR1 :: response = " + responseStr);

            ObjectMapper mapper = new ObjectMapper();

            try {
                peopleResponse = mapper.readValue(responseStr, StarWarsPeopleResponse.class);
            } catch (UnrecognizedPropertyException ex) {
                log.info(() -> "2vQqjTRSq2 :: catch exception = " + ex.getMessage());
                peopleResponse = new StarWarsPeopleResponse();
            }

            StarWarsPeopleResponse finalPeopleResponse = peopleResponse;
            log.info(() -> "r89zJEJqCm :: peopleResponse = " + finalPeopleResponse);
        }

        return peopleResponse;
    }

    @SneakyThrows
    @Override
    public StarWarsPlanetResponse getPlanets(int page) {
        log.info(() -> "s77t2lAmp8 :: getPlanets for page = " + page + " start");

        if (page < 1) {
            throw new RuntimeException("G9ig1661b0 :: page cannot be less than 1");
        }

        String finalUrl = planetsEndpoint + "?page=" + page;

        Request request = new Request.Builder()
                .url(finalUrl)
                .addHeader("accept", "application/json")
                .build();

        log.info(() -> "ZwhKIM4sYU :: request = " + request);

        StarWarsPlanetResponse planetResponse;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new RuntimeException("XWAiT5u35c :: responseBody occurred to be null");
            }

            String responseStr = responseBody.string();

            log.info(() -> "6tzYcFTAKv :: response = " + responseStr);

            ObjectMapper mapper = new ObjectMapper();

            try {
                planetResponse = mapper.readValue(responseStr, StarWarsPlanetResponse.class);
            } catch (UnrecognizedPropertyException ex) {
                log.info(() -> "8E3vqL0MK5 :: catch exception = " + ex.getMessage());
                planetResponse = new StarWarsPlanetResponse();
            }

            StarWarsPlanetResponse finalPlanetResponse = planetResponse;
            log.info(() -> "9zx7a9qrFR :: planetResponse = " + finalPlanetResponse);
        }

        return planetResponse;
    }
}
