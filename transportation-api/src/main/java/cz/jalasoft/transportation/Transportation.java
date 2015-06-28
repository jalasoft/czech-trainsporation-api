package cz.jalasoft.transportation;

import cz.jalasoft.transportation.exception.TransportInfoRetrievalException;
import cz.jalasoft.transportation.exception.TransportRetrievalException;

import java.util.Collection;

/**
 * Created by Honza Lastovicka on 3.5.15.
 */
public interface Transportation {

    Collection<Transport> lookupTransport(String transport) throws TransportRetrievalException;

    Schedule querySchedule(Transport transport) throws TransportInfoRetrievalException;

    Position queryPosition(Transport transport) throws TransportInfoRetrievalException;

    Carrier carrier();
}
