package kz.nkoldassov.nerdapi.client.dto;

import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    public String email;

    @NotBlank
    @Size(min = 6, max = 40)
    public String password;

    public String name;
    public String surname;
}
