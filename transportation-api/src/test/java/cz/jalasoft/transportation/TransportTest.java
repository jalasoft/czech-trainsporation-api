package cz.jalasoft.transportation;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 9/21/15.
 */
public class TransportTest {

    @Test
    public void transportWithoutNameCanBeReconstructedFromItsString() {

        Transport original = Transport
                .newTransport()
                .carrier("Ceske Drahy")
                .code("R 683")
                .get();

        String transportAsString = original.toString();

        Transport reconstructed = Transport.newTransport().fromString(transportAsString);

        assertNotNull(reconstructed);
        assertEquals(reconstructed, original);
    }

    @Test
    public void transportWithNameCanBeReconstructedFromItsString() {

        Transport original = Transport
                .newTransport()
                .carrier("Ceske Drahy")
                .code("R 683")
                .name("Muj vlacek")
                .get();

        String transportAsString = original.toString();

        Transport reconstructed = Transport.newTransport().fromString(transportAsString);

        assertNotNull(reconstructed);
        assertEquals(reconstructed, original);
    }

    @Test
    public void twoDifferentTransportsAeNotEqual() {
        Transport firstOne = Transport
                .newTransport()
                .carrier("Ceske Drahy")
                .code("R 683")
                .name("Muj vlacek")
                .get();

        Transport secondOne = Transport
                .newTransport()
                .carrier("Ceske Drahy")
                .code("R 684")
                .name("Jeji vlacek")
                .get();

        assertNotEquals(firstOne, secondOne);
    }

    @Test
    public void twoSameTransportsAreEqualAndHaveSameHashCode() {
        Transport firstOne = Transport
                .newTransport()
                .carrier("Ceske Drahy")
                .code("R 683")
                .name("Muj vlacek")
                .get();

        Transport secondOne = Transport
                .newTransport()
                .carrier("Ceske Drahy")
                .code("R 683")
                .name("Muj vlacek")
                .get();

        assertEquals(firstOne, secondOne);
        assertEquals(firstOne.hashCode(), secondOne.hashCode());
    }
}
