package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Position;
import cz.jalasoft.transportation.Schedule;
import cz.jalasoft.transportation.Transport;
import cz.jalasoft.transportation.Transportation;
import cz.jalasoft.transportation.exception.TransportInfoRetrievalException;
import cz.jalasoft.transportation.exception.TransportRetrievalException;

import java.util.Collection;

/**
 * Created by honzales on 25.6.15.
 */
public class CzechRailwayTransportation implements Transportation {

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
}
