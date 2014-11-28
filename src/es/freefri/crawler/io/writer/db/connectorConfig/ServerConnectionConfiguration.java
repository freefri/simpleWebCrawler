package es.freefri.crawler.io.writer.db.connectorConfig;

import java.util.HashMap;
import java.util.Map;

abstract public class ServerConnectionConfiguration extends AbstractConnectionConfiguration {
    protected final Map<String, String> properties;
    protected String hostName;
    protected String dbName;

    public ServerConnectionConfiguration(String userName, String password, String hostName) {
        super(userName, password);
        this.properties = new HashMap<String, String>();
        this.hostName = hostName;
    }

    public ServerConnectionConfiguration(
            String userName, String password, String serverName, String dbName) {

        this(userName, password, serverName);
        this.dbName = dbName;
    }

    @Override
    public String getConnectionString() {
        final StringBuilder sb = new StringBuilder("jdbc:");
        sb.append(this.getServerType()).append("://");

        if (hostName != null) {
            sb.append(hostName);
        }

        if (this.getPort() >= 0) {
            sb.append(':').append(this.getPort());
        }

        sb.append('/');

        if (dbName != null) {
            sb.append(dbName);
        }

        boolean isFirst = true;
        for (Map.Entry<String, String> property : this.properties.entrySet()) {
            sb.append(isFirst ? '?' : '&');
            isFirst = false;

            sb.append(property.getKey()).append("=")
                    .append(property.getValue());
        }

        return sb.toString();
    }

    protected abstract String getServerType();

    protected abstract int getPort();
}
