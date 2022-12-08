package kz.nkoldassov.nerdapi.db.jdbs;

import kz.nkoldassov.nerdapi.db.model.RefreshToken;

public interface RefreshTokenRepository {

    int deleteByClient(long clientId);

    RefreshToken getByToken(String token);

    void saveRefreshToken(RefreshToken refreshToken);
}
