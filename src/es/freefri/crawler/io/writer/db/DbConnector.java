package es.freefri.crawler.io.writer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    public static Connection connect(ConnectionConfiguration configuration) throws SQLException {
        return DriverManager.getConnection(
                configuration.getConnectionString(),
                configuration.getConnectionProperties()
        );
    }

}
