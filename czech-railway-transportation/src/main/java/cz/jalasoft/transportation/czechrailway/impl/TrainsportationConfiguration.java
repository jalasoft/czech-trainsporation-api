package cz.jalasoft.transportation.czechrailway.impl;

import cz.jalasoft.util.configuration.annotation.Configuration;
import cz.jalasoft.util.configuration.annotation.Key;
import cz.jalasoft.util.configuration.annotation.SourceType;

/**
 * Created by honzales on 17.5.15.
 */
@Configuration(
        source = SourceType.CLASSPATH,
        name = "configuration.properties"
)
public interface TrainsportationConfiguration {

    @Key("server_host")
    String getHost();

    @Key("server_port")
    int getPort();

    @Key("train_info_path")
    String getTrainInfoPath();
}
