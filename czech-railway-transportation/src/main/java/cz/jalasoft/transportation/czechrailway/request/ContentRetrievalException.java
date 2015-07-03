package cz.jalasoft.transportation.czechrailway.request;

/**
 * Created by honzales on 3.7.15.
 */
public class ContentRetrievalException extends Exception {

    ContentRetrievalException(String reason) {
        super(reason);
    }
}
