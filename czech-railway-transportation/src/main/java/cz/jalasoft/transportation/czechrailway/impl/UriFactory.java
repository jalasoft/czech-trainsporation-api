package cz.jalasoft.transportation.czechrailway.impl;

import cz.jalasoft.util.configuration.Configuration;

import java.net.URI;

import static cz.jalasoft.net.http.URIBuilder.*;

/**
 * Created by honzales on 17.5.15.
 */
final class UriFactory {

    private static final TrainsportationConfiguration config = new Configuration().instantiate(TrainsportationConfiguration.class);

    static URI trainLookupUri() {
        return http()
                .host(config.getHost())
                .port(config.getPort())
                .path(config.getTrainInfoPath())
                .build();
    }
}
