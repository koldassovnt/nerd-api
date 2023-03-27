package kz.nkoldassov.nerdapi.in_service.superhero;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.nkoldassov.nerdapi.configs.SuperHeroApiConfig;
import kz.nkoldassov.nerdapi.in_service.superhero.model.SuperHeroInServiceResponse;
import kz.nkoldassov.nerdapi.logging.LOG;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.Arrays;
import java.util.List;

public class SuperHeroInServiceReal implements SuperHeroInService {

    private static final LOG log = LOG.forClass(SuperHeroInServiceReal.class);

    private final String heroesEndpoint;
    private final String villainsEndpoint;
    private final String apiKey;
    private final String apiHost;

    OkHttpClient client = new OkHttpClient();

    public SuperHeroInServiceReal(SuperHeroApiConfig apiConfig) {
        this.heroesEndpoint = apiConfig.heroesEndpoint();
        this.villainsEndpoint = apiConfig.villainsEndpoint();
        this.apiKey = apiConfig.apiKey();
        this.apiHost = apiConfig.apiHost();
    }


    @SneakyThrows
    @Override
    public List<SuperHeroInServiceResponse> getHeroes() {
        log.info(() -> "JK7khPz12N :: getHeroes start");

        Request request = new Request.Builder()
                .url(heroesEndpoint)
                .addHeader("accept", "application/json")
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", apiHost)
                .build();

        log.info(() -> "F56Bn6HUkG :: request = " + request);

        List<SuperHeroInServiceResponse> heroesInServiceResponseList;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new RuntimeException("nlw19fJ5GF :: responseBody occurred to be null");
            }

            String responseStr = responseBody.string();

            log.info(() -> "CQVDk2UZ66 :: response = " + responseStr);

            ObjectMapper mapper = new ObjectMapper();
            SuperHeroInServiceResponse[] serviceResponseArr = mapper.readValue(responseStr, SuperHeroInServiceResponse[].class);

            heroesInServiceResponseList = Arrays.asList(serviceResponseArr);

            log.info(() -> "z8zvw5kCRY :: heroes response = " + heroesInServiceResponseList);
        }

        return heroesInServiceResponseList;
    }

    @SneakyThrows
    @Override
    public List<SuperHeroInServiceResponse> getVillains() {
        log.info(() -> "YA0fcWJ0K3 :: getVillains start");

        Request request = new Request.Builder()
                .url(villainsEndpoint)
                .addHeader("accept", "application/json")
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", apiHost)
                .build();

        log.info(() -> "t3c1l5c0v0 :: request = " + request);

        List<SuperHeroInServiceResponse> villainsInServiceResponseList;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody == null) {
                throw new RuntimeException("h4kzrQXiNS :: responseBody occurred to be null");
            }

            String responseStr = responseBody.string();

            log.info(() -> "cXM4V2P1L8 :: response = " + responseStr);

            ObjectMapper mapper = new ObjectMapper();
            SuperHeroInServiceResponse[] serviceResponseArr = mapper.readValue(responseStr, SuperHeroInServiceResponse[].class);

            villainsInServiceResponseList = Arrays.asList(serviceResponseArr);

            log.info(() -> "LkeJi9Eq55 :: villains response = " + villainsInServiceResponseList);
        }

        return villainsInServiceResponseList;
    }
}
