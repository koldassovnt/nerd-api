package kz.nkoldassov.nerdapi.in_service.star_wars.model.film;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StarWarsFilmsResponse {
    public Integer count;
    public String next;
    public String previous;
    public List<StarWarsFilmDetailResponse> results = new ArrayList<>();
}
