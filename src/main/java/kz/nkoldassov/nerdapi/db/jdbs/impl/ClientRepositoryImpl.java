package kz.nkoldassov.nerdapi.db.jdbs.impl;

import kz.nkoldassov.nerdapi.db.jdbs.ClientRepository;
import kz.nkoldassov.nerdapi.db.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("SqlDialectInspection")
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Client getClient(Long clientId) {
        return jdbcTemplate.queryForObject(
                "select client, email, password, name, surname, actual, verification_code, email_approved " +
                        "from client " +
                        "where client = ?",
                this::mapToClient, clientId);
    }

    @Override
    public Client getClientByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "select client, email, password, name, surname, actual, verification_code, email_approved " +
                        "from client " +
                        "where email = ?",
                this::mapToClient, email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return Boolean.TRUE.equals(jdbcTemplate.execute(
                "select exists(select 1 from client where email = ?)",
                (PreparedStatementCallback<Boolean>) ps -> {
                    ps.setString(1, email);

                    boolean ret = false;

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            ret = rs.getBoolean(1);
                        }
                    }

                    return ret;
                }));
    }

    @Override
    public void saveClientForRegister(Client client) {
        jdbcTemplate.update("" +
                        "insert into client(email, password, name, surname, verification_code, updated_at) " +
                        "values (?, ?, ?, ?, ?, current_timestamp)",
                ps -> {
                    ps.setString(1, client.email);
                    ps.setString(2, client.password);
                    ps.setString(3, client.name);
                    ps.setString(4, client.surname);
                    ps.setString(5, client.verificationCode);
                });
    }

    @Override
    public Client getClientByVerificationCode(String code) {
        return jdbcTemplate.queryForObject(
                "select client, email, password, name, surname, actual, verification_code, email_approved " +
                        "from client " +
                        "where verification_code = ?",
                this::mapToClient, code);
    }

    @Override
    public void approveClientEmail(Long client) {
        String sql =
                "update client set email_approved = true " +
                        "where client = ?";

        Object[] param = new Object[]{client};

        jdbcTemplate.update(sql, param);
    }

    private Client mapToClient(ResultSet rs, int rowNum) throws SQLException {
        return new Client(
                rs.getLong("client"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getBoolean("actual"),
                rs.getString("verification_code"),
                rs.getBoolean("email_approved")
        );
    }
}
