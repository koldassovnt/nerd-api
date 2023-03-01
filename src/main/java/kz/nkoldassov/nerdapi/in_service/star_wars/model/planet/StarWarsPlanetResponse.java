package kz.nkoldassov.nerdapi.in_service.star_wars.model.planet;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class StarWarsPlanetResponse {
    public Integer count;
    public String next;
    public String previous;
    public List<StarWarsPlanetDetailResponse> results = new ArrayList<>();
}
