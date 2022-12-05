package kz.nkoldassov.nerdapi.client.dto;

import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
public class RefreshTokenRequest {

    @NotBlank
    public String refreshToken;
}
