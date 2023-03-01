package kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class LordOfTheRingsMovieDetailResponse {

    @JsonProperty("_id")
    public String id;
    public String name;
    public Integer runtimeInMinutes;
    public Integer budgetInMillions;
    public Integer boxOfficeRevenueInMillions;
    public Integer academyAwardNominations;
    public Integer academyAwardWins;
    public Integer rottenTomatoesScore;
}
