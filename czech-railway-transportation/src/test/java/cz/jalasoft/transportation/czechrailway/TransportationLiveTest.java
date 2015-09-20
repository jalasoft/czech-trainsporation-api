package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Schedule;
import cz.jalasoft.transportation.Transport;
import cz.jalasoft.transportation.Transportation;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;

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
    public void findsATrainViaMultipleTrainsPage() throws TransportRetrievalException {
        Collection<Transport> result = transportation.lookupTransport("864");
        Transport t = result.iterator().next();

        /*String code = t.code();
        assertEquals("R 864", code);

        Optional<String> maybeName = t.name();
        assertFalse(maybeName.isPresent());

        String fullId = t.fullName
        assertEquals("R 864", fullId);
        */
        System.out.println(t);
    }

    @Test
    public void findsNothing() throws TransportRetrievalException {
        Collection<Transport> result = transportation.lookupTransport("Blbost");

        assertTrue(result.isEmpty());
    }

    @Test
    public void findsATrainDirectly() throws TransportRetrievalException {
        Collection<Transport> result = transportation.lookupTransport("Ex 152");
        Transport t = result.iterator().next();

        System.out.println(t);

        /*
        String code = t.code();
        assertEquals("Ex 152", code);

        Optional<String> maybeName = t.name();
        assertTrue(maybeName.isPresent());

        assertEquals("Hukvaldy", maybeName.get());

        String fullId = t.fullName();
        assertEquals("Ex 152 (Hukvaldy)", fullId);*/
    }

    @Test
    public void test() throws Exception {
        Collection<Transport> result = transportation.lookupTransport("Ex 152");
        Transport t = result.iterator().next();

        Schedule s = transportation.querySchedule(t);

    }
}
