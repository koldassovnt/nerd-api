package kz.nkoldassov.nerdapi.db.jdbs.impl;

import kz.nkoldassov.nerdapi.db.jdbs.ClientRepository;
import kz.nkoldassov.nerdapi.db.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Client getClient(Long clientId) {
        return jdbcTemplate.queryForObject(
                "select client, email, password, name, surname " +
                        "from client " +
                        "where client = ?",
                this::mapToClient, clientId);
    }

    @Override
    public Client getClientByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "select client, email, password, name, surname " +
                        "from client " +
                        "where email = ?",
                this::mapToClient, email);
    }

    private Client mapToClient(ResultSet rs, int rowNum) throws SQLException {
        return new Client(
                rs.getLong("client"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getBoolean("actual")
        );
    }
}
