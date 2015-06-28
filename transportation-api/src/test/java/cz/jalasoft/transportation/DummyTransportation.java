package cz.jalasoft.transportation;

import cz.jalasoft.transportation.exception.TransportInfoRetrievalException;
import cz.jalasoft.transportation.exception.TransportRetrievalException;

import java.util.Collection;

/**
 * Created by honzales on 28.6.15.
 */
public class DummyTransportation implements Transportation {

    @Override
    public Collection<Transport> lookupTransport(String transport) throws TransportRetrievalException {
        return null;
    }

    @Override
    public Schedule querySchedule(Transport transport) throws TransportInfoRetrievalException {
        return null;
    }

    @Override
    public Position queryPosition(Transport transport) throws TransportInfoRetrievalException {
        return null;
    }

    @Override
    public Carrier carrier() {
        return new Carrier() {
            @Override
            public String name() {
                return "Dummy Transportation";
            }
        };
    }
}
