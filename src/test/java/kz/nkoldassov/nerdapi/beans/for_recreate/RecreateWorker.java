package kz.nkoldassov.nerdapi.beans.for_recreate;

import kz.nkoldassov.nerdapi.beans.all.LiquibaseManager;
import kz.nkoldassov.nerdapi.beans.all.ShowConfigValuesOnStartup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class RecreateWorker {

    @Autowired
    private ShowConfigValuesOnStartup showConfigValuesOnStartup;

    @Autowired
    private LiquibaseManager liquibaseManager;

    public void recreate() throws Exception {

        showConfigValuesOnStartup.show();

        liquibaseManager.applyChangeSets();

    }

    @SuppressWarnings("SqlDialectInspection")
    public static void recreatePostgresDatabase(String dbName, String username) throws Exception {

        try (var connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:20018/postgres",
                "postgres", "postgres"
        )) {

            while (true) try (var statement = connection.createStatement()) {
                statement.execute("drop database " + dbName);
                break;
            } catch (SQLException e) {

                if ("3D000".equals(e.getSQLState())) {
                    break;
                }

                /*this error occurs when somebody connected to db*/
                if ("55006".equals(e.getSQLState())) {

                    // drop all connections to db
                    try (var statement = connection.createStatement()) {
                        statement.execute("SELECT pg_terminate_backend(pg_stat_activity.pid)\n" +
                                "FROM pg_stat_activity\n" +
                                "WHERE pg_stat_activity.datname = '" + dbName + "'");
                    }

                    //drop db again
                    continue;

                }

                throw new RuntimeException("JyhSBz4pvO :: SQLException with e.getSQLState() = `" + e.getSQLState() + '`', e);
            }

            try (var statement = connection.createStatement()) {
                statement.execute("create database " + dbName);
            }
            try (var statement = connection.createStatement()) {
                statement.execute("GRANT ALL ON DATABASE " + dbName + " TO " + username);
            }

        }
    }
}
