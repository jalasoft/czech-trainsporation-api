package cz.jalasoft.transportation;


import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.util.Collection;


/**
 *
 */
public class TransportationsTest {

    @Test
    public void loadsDummyTrasnportationThatLiesOnClasspath() {
        Collection<Carrier> carriers = Transportations.availableCarriers();
        assertEquals(carriers.size(), 1);
    }

    @Test
    public void looksUpCarrierByItsName() {
        Carrier carrier = Transportations.findCarrier("Dummy Transportation");

        assertNotNull(carrier);
        assertEquals(carrier.name(), "Dummy Transportation");
    }

    @Test
    public void getsNotNullTrasnportationForDummyCarrier() {
        Carrier carrier = Transportations.findCarrier("Dummy Transportation");
        Transportation tr = Transportations.forCarrier(carrier);

        assertNotNull(tr);
        assertEquals(carrier.name(), "Dummy Transportation");
    }

    private Carrier carrier(String carrier) {
        return () -> carrier;
    }
}
