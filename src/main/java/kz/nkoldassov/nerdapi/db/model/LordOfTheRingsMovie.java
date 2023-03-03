package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LordOfTheRingsMovie {
    public Long id;
    public String externalId;
    public String name;
    public Integer runtimeInMinutes;
    public Integer budgetInMillions;
    public Integer boxOfficeRevenueInMillions;
    public Integer academyAwardNominations;
    public Integer academyAwardWins;
    public Integer rottenTomatoesScore;
}
