package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    public long id;
    public long clientId;
    public String token;
    public Date expiryDate;
    public Boolean actual;

    public boolean actual() {
        return actual != null && actual;
    }
}
