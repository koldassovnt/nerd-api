package kz.nkoldassov.nerdapi.client.dto;

import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
public class LoginRequest {

    @NotBlank
    public String email;

    @NotBlank
    public String password;
}
