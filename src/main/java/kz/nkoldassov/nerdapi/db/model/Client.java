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
    public String verificationCode;
    public Boolean emailApproved;

    public static Client forRegister(String email, String password, String name, String surname, String verificationCode) {
        Client ret = new Client();
        ret.email = email;
        ret.password = password;
        ret.name = name;
        ret.surname = surname;
        ret.verificationCode = verificationCode;
        ret.actual = true;

        return ret;
    }

    public Client(Long client, String email, String password, String name, String surname, Boolean actual) {
        this.client = client;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.actual = actual;
    }

    public boolean actual() {
        return actual != null && actual;
    }

    public String fio() {

        if (name == null) name = "";

        if (surname == null) surname = "";

        return name + " " + surname;
    }
}
