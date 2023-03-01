package kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class LordOfTheRingsMovieResponse {
    public List<LordOfTheRingsMovieDetailResponse> docs = new ArrayList<>();
    public Integer total;
    public Integer limit;
    public Integer offset;
    public Integer page;
    public Integer pages;
}
