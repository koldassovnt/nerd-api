package kz.nkoldassov.nerdapi.beans.all;

import kz.nkoldassov.nerdapi.configs.DbConfig;
import liquibase.Liquibase;
import liquibase.database.core.PostgresDatabase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class LiquibaseManager {

    @Autowired
    private DbConfig dbConfig;

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(
                dbConfig.url(),
                dbConfig.username(),
                dbConfig.password()
        );
    }

    public void applyChangeSets() throws Exception {
        Class.forName("org.postgresql.Driver");

        try (var connection = openConnection()) {
            applyChangeSetsToConnection(connection);
        }
    }

    public void applyChangeSetsToConnection(Connection connection) throws LiquibaseException {
        var database = new PostgresDatabase();

        database.setConnection(new JdbcConnection(connection));

        {
            new Liquibase(
                    "liquibase/master-change-log.xml",
                    new ClassLoaderResourceAccessor(), database
            ).update("");
        }
    }
}
