package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Transport;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/2/15.
 */
public class TransportationTest {

    private CzechRailwayWebdriverTransportation transportation;

    @BeforeClass
    public void init() {
        transportation = new CzechRailwayWebdriverTransportation();
    }

    @Test
    public void test1() throws TransportRetrievalException {
        long s1 = System.currentTimeMillis();
        Collection<Transport> transports = transportation.findTransport("Hasek");
        long s2 = System.currentTimeMillis();



        System.out.println(s2 - s1);
    }
}
