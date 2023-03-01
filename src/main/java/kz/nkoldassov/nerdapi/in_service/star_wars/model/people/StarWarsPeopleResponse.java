package kz.nkoldassov.nerdapi.in_service.star_wars.model.people;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StarWarsPeopleResponse {
    public Integer count;
    public String next;
    public String previous;
    public List<StarWarsPersonDetailResponse> results = new ArrayList<>();
}
