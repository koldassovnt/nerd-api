package kz.nkoldassov.nerdapi.in_service.superhero.model;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Appearance {
    public String gender;
    public String race;
    public List<String> height = new ArrayList<>();
    public List<String> weight = new ArrayList<>();
    public String eyeColor;
    public String hairColor;
}
