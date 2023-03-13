package kz.nkoldassov.nerdapi.util;

import org.apache.logging.log4j.util.Strings;

public class InServiceUtil {

    /**
     * Пример url:
     * 1) <a href="https://swapi.dev/api/planets/12/">...</a>
     * 2) <a href="https://swapi.dev/api/films/6/">...</a>
     * 3) <a href="https://swapi.dev/api/people/83/">...</a>
     */
    public static String getIdFromStarWarsApiUrl(String url) {

        if (Strings.isBlank(url)) {
            return null;
        }

        return url.replaceAll("https://swapi.dev/api/", "");
    }
}
