package cz.jalasoft.transportation;


import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


/**
 *
 */
public class TransportationsTest {

    @Test(enabled = false)
    public void loadsDummyTrasnportationThatLiesOnClasspath() {
        Collection<Carrier> carriers = Transportations.availableCarriers();
        assertEquals(carriers.size(), 1);
    }

    @Test(enabled = false)
    public void looksUpCarrierByItsName() {
        Carrier carrier = Transportations.findCarrier("Dummy Transportation");

        assertNotNull(carrier);
        assertEquals(carrier.name(), "Dummy Transportation");
    }

    @Test(enabled = false)
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
