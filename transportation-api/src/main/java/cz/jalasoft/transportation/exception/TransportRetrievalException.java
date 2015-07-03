package cz.jalasoft.transportation.exception;

/**
 * Created by honzales on 7.6.15.
 */
public class TransportRetrievalException extends Exception {

    private final String transport;

    public TransportRetrievalException(String transport, Exception cause) {
        super(cause);

        this.transport = transport;
    }

    public TransportRetrievalException(String transport, String cause) {
        super(cause);

        this.transport = transport;
    }

    public String getTransport() {
        return transport;
    }
}
