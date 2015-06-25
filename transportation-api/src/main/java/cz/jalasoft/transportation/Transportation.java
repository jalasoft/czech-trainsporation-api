package cz.jalasoft.transportation;

import cz.jalasoft.transportation.aggregate.AggregatingTransportation;
import cz.jalasoft.transportation.exception.TransportInfoRetrievalException;
import cz.jalasoft.transportation.exception.TransportRetrievalException;

import java.util.Collection;

/**
 * Created by Honza Lastovicka on 3.5.15.
 */
public abstract class Transportation {

    private static final Transportation INSTANCE = new AggregatingTransportation();

    public static Transportation get() {
        return INSTANCE;
    }

    public abstract Collection<Transport> lookupTransport(String transport) throws TransportRetrievalException;

    public abstract Schedule querySchedule(Transport transport) throws TransportInfoRetrievalException;

    public abstract Position queryPosition(Transport transport) throws TransportInfoRetrievalException;
}
