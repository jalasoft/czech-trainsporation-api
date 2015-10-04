package cz.jalasoft.transportation.czechrailway;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/2/15.
 */
final class Configuration {

    private final Config config;

    Configuration() {
        config = ConfigFactory.load();
    }

    String trainInfoUrl() {
        String protocol = config.getString("transportation.server.protocol");
        String host = config.getString("transportation.server.host");
        int port = config.getInt("transportation.server.port");
        String path = config.getString("transportation.path.lookup");

        String result = protocol + "://" + host + ":" + port + path;
        return result;
    }

    int webTimeout() {
        return config.getInt("transportation.web.wait-timeout-seconds");
    }
}
