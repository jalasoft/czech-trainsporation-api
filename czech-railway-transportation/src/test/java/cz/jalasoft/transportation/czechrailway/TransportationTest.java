package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Transportation;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by honzales on 30.6.15.
 */
public class TransportationTest {

    private Transportation transportation;

    @Before
    public void init() {
        transportation = new CzechRailwayTransportation();
    }

    @Test
    public void test() throws TransportRetrievalException {
        transportation.lookupTransport("144");
    }
}
