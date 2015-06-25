package cz.jalasoft.transportation.exception;

import cz.jalasoft.transportation.Transport;

/**
 * Created by honzales on 3.5.15.
 */
public final class TransportInfoRetrievalException extends Exception {

    private final Transport transport;

    public TransportInfoRetrievalException(Transport transport) {
        this.transport = transport;
    }

}
