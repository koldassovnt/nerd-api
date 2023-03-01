package kz.nkoldassov.nerdapi.in_service.lord_of_the_rings.model.character;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class LordOfTheRingsCharacterResponse {
    public List<LordOfTheRingsCharacterDetailResponse> docs = new ArrayList<>();
    public Integer total;
    public Integer limit;
    public Integer offset;
    public Integer page;
    public Integer pages;
}
