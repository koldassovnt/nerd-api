package kz.nkoldassov.nerdapi.db.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private long client;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Boolean actual;

    public boolean actual() {
        return actual != null && actual;
    }
}
