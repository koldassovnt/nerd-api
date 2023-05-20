package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroOrVillain {
    public Long id;
    public Integer externalId;
    public String name;
    public String slug;
    public String fullName;
    public String alterEgos;
    public String aliases;
    public String firstAppearance;
    public String publisher;
    public String alignment;
    public String imageUrl;
}
