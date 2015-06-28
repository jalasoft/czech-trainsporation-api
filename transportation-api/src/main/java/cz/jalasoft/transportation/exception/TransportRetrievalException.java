package cz.jalasoft.transportation.exception;

/**
 * Created by honzales on 7.6.15.
 */
public class TransportRetrievalException extends Exception {

    private final String transportDescription;

    public TransportRetrievalException(String transportDescription, String cause) {
        super(cause);

        this.transportDescription = transportDescription;
    }

    public String getTransportDescription() {
        return transportDescription;
    }
}
