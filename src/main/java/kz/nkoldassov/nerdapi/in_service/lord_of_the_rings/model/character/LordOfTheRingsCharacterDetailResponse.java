package kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class LordOfTheRingsCharacterDetailResponse {

    @JsonProperty("_id")
    public String id;
    public String height;
    public String race;
    public String gender;
    public String birth;
    public String spouse;
    public String death;
    public String realm;
    public String hair;
    public String name;
    public String wikiUrl;
}
