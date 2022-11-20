package kz.nkoldassov.nerdapi.in_service.random_fact;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RandomFactInServiceReal implements RandomFactInService {

    private final String requestUrl;
    private final String apiKey;

    public RandomFactInServiceReal(String requestUrl, String apiKey) {
        this.requestUrl = requestUrl;
        this.apiKey = apiKey;
    }

    @SneakyThrows
    @Override
    public String getRandomFact() {
        URL url = new URL(requestUrl + "1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-Api-Key", apiKey);

        String fact = "RESPONSE ERROR";

        try (InputStream responseStream = connection.getInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseStream);

            List<String> factList = root.findValuesAsText("fact");

            if (!factList.isEmpty()) {
                fact = factList.get(0);
            }
        }

        return fact;
    }
}
