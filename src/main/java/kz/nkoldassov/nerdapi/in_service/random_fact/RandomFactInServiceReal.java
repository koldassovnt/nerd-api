package kz.nkoldassov.nerdapi.in_service.random_fact;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.nkoldassov.nerdapi.in_service.random_fact.model.FactResponse;
import kz.nkoldassov.nerdapi.logging.LOG;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.Arrays;

import static kz.nkoldassov.nerdapi.util.ServiceCallStatic.RESPONSE_ERROR_TEXT;

public class RandomFactInServiceReal implements RandomFactInService {

    private static final LOG log = LOG.forClass(RandomFactInServiceReal.class);

    private final String requestUrl;
    private final String apiKey;

    OkHttpClient client = new OkHttpClient();

    public RandomFactInServiceReal(String requestUrl, String apiKey) {
        this.requestUrl = requestUrl;
        this.apiKey = apiKey;
    }

    @SneakyThrows
    @Override
    public String getRandomFact() {

        log.info(() -> "7096vpW3Jt :: requestURl = " + requestUrl + ", apiKey = " + apiKey);

        Request request = new Request.Builder()
                .url(requestUrl + "1")
                .addHeader("accept", "application/json")
                .addHeader("X-Api-Key", apiKey)
                .build();

        log.info(() -> "zKfXCt15vo :: request = " + request);

        String fact = RESPONSE_ERROR_TEXT;

        Call call = client.newCall(request);
        try (Response response = call.execute()) {

            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                String responseStr = responseBody.string();

                log.info(() -> "36Z9MixZP9 :: response = " + responseStr);

                ObjectMapper mapper = new ObjectMapper();
                FactResponse[] factResponses = mapper.readValue(responseStr, FactResponse[].class);

                log.info(() -> "tCuz5l8e8e :: factResponses = " + Arrays.toString(factResponses));

                if (factResponses.length > 0) {
                    fact = factResponses[0].fact;
                } else {
                    log.error("z09VAxAeYg :: factResponse is empty");
                }
            } else {
                log.error("leRj943xCM :: responseBody was null!");
            }
        }

        return fact;
    }
}
