package es.freefri.crawler.io.writer.db.connectorConfig;

import es.freefri.crawler.io.writer.db.ConnectionConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MySQLConnectionConfigurationTest {

    @Test
    public void testGetConnectionString() throws Exception {
        ConnectionConfiguration cc = new MySQLConnectionConfiguration(
                "us", "pass", "localhost"
        );
        assertEquals("jdbc:mysql://localhost:3306/", cc.getConnectionString());
        assertEquals("{user=us, password=pass}", cc.getConnectionProperties().toString());
    }

    @Test
    public void testGetConnectionStringChangingPortAndDb() throws Exception {
        ConnectionConfiguration cc = new MySQLConnectionConfiguration(
                "us", "pass", "localhost", "db", 3338
        );
        assertEquals("jdbc:mysql://localhost:3338/db", cc.getConnectionString());
        assertEquals("{user=us, password=pass}", cc.getConnectionProperties().toString());
    }
}