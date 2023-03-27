package kz.nkoldassov.nerdapi.in_service.superhero.model;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Biography {
    public String fullName;
    public String alterEgos;
    public List<String> aliases = new ArrayList<>();
    public String placeOfBirth;
    public String firstAppearance;
    public String publisher;
    public String alignment;
}
