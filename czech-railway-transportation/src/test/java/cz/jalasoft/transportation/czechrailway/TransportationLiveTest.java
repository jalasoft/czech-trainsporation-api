package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Transport;
import cz.jalasoft.transportation.Transportation;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by honzales on 30.6.15.
 */
public class TransportationLiveTest {

    private Transportation transportation;

    @Before
    public void init() {
        transportation = new CzechRailwayTransportation();
    }

    @Test
    public void test() throws TransportRetrievalException {
        Collection<Transport> result = transportation.lookupTransport("Hasek");
        Transport t = result.iterator().next();

        String code = t.code();
        Optional<String> maybeName = t.name();
        String fullId = t.fullIdentification();

        System.out.println("code: " + code + ", name: " + maybeName + ", full: " + fullId);
    }
}
