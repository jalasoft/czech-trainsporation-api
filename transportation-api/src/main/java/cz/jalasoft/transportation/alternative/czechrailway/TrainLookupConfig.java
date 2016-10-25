package cz.jalasoft.transportation.alternative.czechrailway;

import cz.jalasoft.lifeconfig.annotation.KeyPrefix;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-04.
 */
@KeyPrefix("transportation.czech_railway.train_lookup")
public interface TrainLookupConfig {

    String host();

    int port();

    String path();

    default String uriString() {
        return "http://" + host() + ":" + port() + path();
    }
}
