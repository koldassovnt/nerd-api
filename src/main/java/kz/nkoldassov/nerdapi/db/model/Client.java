package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    public Long client;
    public String email;
    public String password;
    public String name;
    public String surname;
    public Boolean actual;

    public static Client register(String email, String password) {
        Client ret = new Client();
        ret.email = email;
        ret.password = password;
        ret.actual = true;

        return ret;
    }

    public boolean actual() {
        return actual != null && actual;
    }
}
