package kz.nkoldassov.nerdapi.client.dto;

import lombok.ToString;

@ToString
public class TokenRefreshResponse {
    public String accessToken;
    public String refreshToken;
    public String tokenType = "Bearer";

    public static TokenRefreshResponse of(String accessToken, String refreshToken) {
        TokenRefreshResponse res = new TokenRefreshResponse();
        res.accessToken = accessToken;
        res.refreshToken = refreshToken;

        return res;
    }
}
