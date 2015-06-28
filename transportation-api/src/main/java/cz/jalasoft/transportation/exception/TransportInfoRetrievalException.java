package cz.jalasoft.transportation.exception;

import cz.jalasoft.transportation.Transport;

/**
 * Created by honzales on 3.5.15.
 */
public abstract class TransportInfoRetrievalException extends Exception {

    private final Transport transport;

    public TransportInfoRetrievalException(Transport transport, String cause) {
        super(cause);
        this.transport = transport;
    }

    public Transport getTransport() {
        return transport;
    }
}
