package kz.nkoldassov.nerdapi.client.dto;

import lombok.ToString;

@ToString
public class TokenRefreshResponse {
    public String accessToken;
    public String refreshToken;
    public String tokenType = "Bearer";
}
