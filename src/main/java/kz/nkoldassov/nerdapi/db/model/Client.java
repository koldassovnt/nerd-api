package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    public long client;
    public String email;
    public String password;
    public String name;
    public String surname;
    public Boolean actual;

    public boolean actual() {
        return actual != null && actual;
    }
}
