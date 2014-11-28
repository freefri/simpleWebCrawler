package es.freefri.crawler.io.writer.db;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class DbConnectorTest {

    @Test(expected = CommunicationsException.class)
    public void throwsExceptionIfConnectionFails() throws Exception {
        String connectionStr = "jdbc:mysql://localhost:9999/crawler";
        DbConnector.connect(new MockedConnectionConfig(connectionStr));
    }

    @Test(expected = SQLException.class)
    public void throwsExceptionIfInvalidUrl() throws Exception {
        DbConnector.connect(new MockedConnectionConfig("jdbc:"));
    }

    @Test
    public void connectsToDbServer() throws Exception {
        String connectionStr = "jdbc:mysql://";
        ConnectionConfiguration cc = new MockedConnectionConfig(connectionStr);
        Connection connection = DbConnector.connect(cc);
        assertNull(connection.getSchema());
        assertFalse(connection.isClosed());
    }

    @Test
    public void connectsToMysql() throws Exception {
        String connectionStr = "jdbc:mysql://localhost:3306/crawler";
        ConnectionConfiguration cc = new MockedConnectionConfig(connectionStr);
        Connection connection = DbConnector.connect(cc);
        assertFalse(connection.isClosed());
    }

    @Test
    public void connectsToPostgresql() throws Exception {
        String connectionStr = "jdbc:postgresql://localhost:5432/crawler";
        ConnectionConfiguration cc = new MockedConnectionConfig(connectionStr);
        Connection connection = DbConnector.connect(cc);
        assertFalse(connection.isClosed());
    }

    class MockedConnectionConfig implements ConnectionConfiguration {

        private String connectionStr = null;

        MockedConnectionConfig(String connectionStr) {
            this.connectionStr = connectionStr;
        }

        @Override
        public String getConnectionString() {
            return connectionStr;
        }

        @Override
        public Properties getConnectionProperties() {
            final Properties properties = new Properties();
            properties.put("user", "dev");
            properties.put("password", "1234");
            return properties;
        }
    }
}