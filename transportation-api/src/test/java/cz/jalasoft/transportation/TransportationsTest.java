package cz.jalasoft.transportation;

import org.junit.Test;

import java.util.Collection;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by honzales on 28.6.15.
 */
public class TransportationsTest {

    @Test
    public void loadsDummyTrasnportationThatLiesOnClasspath() {

        Collection<Carrier> carriers = Transportations.availableCarriers();
        assertThat(carriers.size(), is(equalTo(1)));
    }

    @Test
    public void looksUpCarrierByItsName() {
        Carrier carrier = Transportations.lookupCarrier("Dummy Transportation");
        assertThat(carrier, is(notNullValue()));
        assertThat(carrier.name(), is(equalTo("Dummy Transportation")));
    }

    @Test
    public void getsNotNullTrasnportationForDummyCarrier() {
        Carrier carrier = Transportations.lookupCarrier("Dummy Transportation");
        Transportation tr = Transportations.transportation(carrier);

        assertThat(tr, is(notNullValue()));
    }
}
