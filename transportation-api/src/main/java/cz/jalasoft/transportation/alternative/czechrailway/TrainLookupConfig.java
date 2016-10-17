package cz.jalasoft.transportation.alternative.czechrailway;

import cz.jalasoft.lifeconfig.annotation.KeyPrefix;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-04.
 */
@KeyPrefix("transportation.czech_railway.train_lookup")
public interface TrainLookupConfig {

    String host();

    int port();

    String path();

    default URL uri() {
        try {
            return new URL("http", host(), port(), path());
        } catch (MalformedURLException exc) {
            throw new RuntimeException(exc);
        }
    }

    default String uriString() {
        return "http://" + host() + ":" + port() + path();
    }
}
