package cz.jalasoft.transportation.exception;

/**
 * Created by honzales on 7.6.15.
 */
public final class TransportRetrievalException extends Exception {

    private final String transportDescription;

    public TransportRetrievalException(String transportDescription, String message) {
        super(message);

        this.transportDescription = transportDescription;
    }
}
