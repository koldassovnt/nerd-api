package kz.nkoldassov.nerdapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello at " + new Date() + "!";
    }

    @SneakyThrows
    @GetMapping("/fact")
    public String fact() {
        URL url = new URL("https://api.api-ninjas.com/v1/facts?limit=1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-Api-Key", "A5SL+QhMVyb3Qa5sfsyFjA==zRkN6cZiKc5vqhIR");

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
