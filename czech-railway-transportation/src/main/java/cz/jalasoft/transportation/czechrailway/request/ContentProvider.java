package cz.jalasoft.transportation.czechrailway.request;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.transportation.czechrailway.config.CzechRailwayConnectionInfo;
import cz.jalasoft.util.configuration.Configuration;
import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 3.7.15.
 */
public abstract class ContentProvider<T> {

    private final HttpClient client;
    private final CzechRailwayConnectionInfo config;

    public ContentProvider(HttpClient client) {
        this.client = client;
        this.config = new Configuration().instantiate(CzechRailwayConnectionInfo.class);
    }

    final HttpClient client() {
        return client;
    }

    final CzechRailwayConnectionInfo config() {
        return config;
    }

    public abstract TextFragment getContent(T input) throws ContentRetrievalException;
}
