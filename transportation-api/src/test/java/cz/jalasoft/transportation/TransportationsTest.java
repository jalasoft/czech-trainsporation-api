package cz.jalasoft.transportation;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by honzales on 28.6.15.
 */
public class TransportationsTest {

    @Test
    public void test() {

        Collection<Carrier> carriers = Transportations.availaleCarriers();
    }
}
