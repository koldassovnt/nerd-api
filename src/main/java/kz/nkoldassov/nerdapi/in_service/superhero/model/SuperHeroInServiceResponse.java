package kz.nkoldassov.nerdapi.in_service.superhero.model;

import lombok.ToString;

@ToString
public class SuperHeroInServiceResponse {
    public Integer id;
    public String name;
    public String slug;
    public PowerStats powerstats;
    public Appearance appearance;
    public Biography biography;
    public Work work;
    public Connections connections;
    public Images images;
}
