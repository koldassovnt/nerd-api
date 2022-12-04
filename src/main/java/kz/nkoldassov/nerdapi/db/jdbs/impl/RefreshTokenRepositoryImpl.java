package kz.nkoldassov.nerdapi.db.jdbs.impl;

import kz.nkoldassov.nerdapi.db.jdbs.RefreshTokenRepository;
import kz.nkoldassov.nerdapi.db.model.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@SuppressWarnings("SqlDialectInspection")
@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int deleteByClient(long clientId) {
        String sql =
                "update refresh_token set actual = false " +
                        "where client_id = ?";

        Object[] param = new Object[]{clientId};

        return jdbcTemplate.update(sql, param);
    }

    @Override
    public RefreshToken getByToken(String token) {
        return jdbcTemplate.queryForObject(
                "select id, " +
                        "client_id as clientId, " +
                        "token, " +
                        "expiry_date as expiryDate, " +
                        "actual " +
                        "from refresh_token " +
                        "where token = ?",
                this::mapToRefreshToken, token);
    }

    private RefreshToken mapToRefreshToken(ResultSet rs, int rowNum) throws SQLException {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.id = rs.getLong("id");
        refreshToken.clientId = rs.getLong("clientId");
        refreshToken.token = rs.getString("token");
        refreshToken.actual = rs.getBoolean("actual");

        Timestamp expiryDate = rs.getTimestamp("expiryDate");
        refreshToken.expiryDate = new Date(expiryDate.getTime());

        return refreshToken;
    }
}
