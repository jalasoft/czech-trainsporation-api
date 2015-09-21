package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Schedule;
import cz.jalasoft.transportation.Transport;
import cz.jalasoft.transportation.Transportation;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collection;

/**
 * Created by honzales on 30.6.15.
 */
public class TransportationLiveTest {

    private Transportation transportation;

    @BeforeTest
    public void init() {
        transportation = new CzechRailwayTransportation();
    }

    @Test
    public void findsATrainViaMultipleTrainsPage() throws TransportRetrievalException {
        Collection<Transport> result = transportation.findTransport("R865");
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
        Collection<Transport> result = transportation.findTransport("Těsnohlídek");

        throw new AssertionError();
    }

    @Test
    public void findsATrainDirectly() throws TransportRetrievalException {
        Collection<Transport> result = transportation.findTransport("Ex 152");
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
        Collection<Transport> result = transportation.findTransport("Ex 152");
        Transport t = result.iterator().next();

        Schedule s = transportation.querySchedule(t);

    }
}
