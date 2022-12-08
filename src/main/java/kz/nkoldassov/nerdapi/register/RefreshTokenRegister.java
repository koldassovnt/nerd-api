package kz.nkoldassov.nerdapi.register;

import kz.nkoldassov.nerdapi.client.dto.RefreshTokenRequest;
import kz.nkoldassov.nerdapi.db.model.RefreshToken;

public interface RefreshTokenRegister {

    RefreshToken getByToken(RefreshTokenRequest request);

    RefreshToken createRefreshToken(Long clientId);

    RefreshToken verifyExpiration(RefreshToken clientId);

    int deleteRefreshTokenByClient(Long clientId);
}
