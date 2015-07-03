package cz.jalasoft.transportation.czechrailway.config;

import cz.jalasoft.util.configuration.annotation.Configuration;
import cz.jalasoft.util.configuration.annotation.Key;
import cz.jalasoft.util.configuration.annotation.SourceType;

/**
 * Created by Honza Lastovicka on 30.6.15.
 */
@Configuration(
        source = SourceType.CLASSPATH,
        name = "configuration.properties"
)
public interface CzechRailwayConnectionInfo {

    @Key("server_host")
    String getHost();

    @Key("server_port")
    int getPort();

    @Key("train_info_path")
    String getTrainInfoPath();
}
