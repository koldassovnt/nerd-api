package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LordOfTheRingsCharacter {
    public Long id;
    public String externalId;
    public String height;
    public String race;
    public String gender;
    public String birth;
    public String spouse;
    public String death;
    public String realm;
    public String hair;
    public String name;
}
