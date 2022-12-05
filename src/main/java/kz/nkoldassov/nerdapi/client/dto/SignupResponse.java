package kz.nkoldassov.nerdapi.client.dto;

import lombok.ToString;

@ToString
public class SignupResponse {
    public String token;
    public String type = "Bearer";
    public String refreshToken;
    public Long client;
    public String email;
}
