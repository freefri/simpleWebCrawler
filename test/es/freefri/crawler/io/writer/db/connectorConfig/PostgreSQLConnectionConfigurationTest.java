package es.freefri.crawler.io.writer.db.connectorConfig;

import es.freefri.crawler.io.writer.db.ConnectionConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostgreSQLConnectionConfigurationTest {

    @Test
    public void testGetConnectionString() throws Exception {
        ConnectionConfiguration cc = new PostgreSQLConnectionConfiguration(
                "us", "pass", "localhost"
        );
        assertEquals("jdbc:postgresql://localhost:5432/", cc.getConnectionString());
        assertEquals("{user=us, password=pass}", cc.getConnectionProperties().toString());
    }

    @Test
    public void testGetConnectionStringChangingPortAndDb() throws Exception {
        ConnectionConfiguration cc = new PostgreSQLConnectionConfiguration(
                "us", "pass", "localhost", "db", 3338
        );
        assertEquals("jdbc:postgresql://localhost:3338/db", cc.getConnectionString());
        assertEquals("{user=us, password=pass}", cc.getConnectionProperties().toString());
    }
}