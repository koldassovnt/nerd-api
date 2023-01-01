package kz.nkoldassov.nerdapi.db.impl;

import kz.nkoldassov.nerdapi.db.jdbs.RefreshTokenRepository;
import kz.nkoldassov.nerdapi.db.model.RefreshToken;
import kz.nkoldassov.nerdapi.logging.LOG;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@SuppressWarnings("SqlDialectInspection")
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private static final LOG log = LOG.forClass(RefreshTokenRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public RefreshTokenRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

    @Override
    public void saveRefreshToken(RefreshToken refreshToken) {
        Object[] params = new Object[4];
        params[0] = refreshToken.clientId;
        params[1] = refreshToken.token;
        params[2] = refreshToken.expiryDate;
        params[3] = refreshToken.actual();

        int inserted = jdbcTemplate.update(
                "insert into refresh_token(client_id, token, expiry_date, actual) " +
                        "values (?, ?, ?, ?) " +
                        "on conflict (client_id) do update " +
                        "    set token       = excluded.token, " +
                        "        actual      = true, " +
                        "        expiry_date = excluded.expiry_date",
                params);

        log.info(() -> "Q2Ev86CUQh :: refreshToken inserted = " + inserted);
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
