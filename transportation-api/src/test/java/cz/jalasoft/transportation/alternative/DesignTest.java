package cz.jalasoft.transportation.alternative;

import cz.jalasoft.transportation.alternative.czechrailway.CzechRailwayTransportation;
import cz.jalasoft.transportation.alternative.czechrailway.Train;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-07-25.
 */
public class DesignTest {

    @Test
    public void test() throws IOException, TransportLookupException {

        Transportation<Train> transportation = CzechRailwayTransportation.czechRailways();

        Collection<Train> trains = transportation.lookup("Hasek");

        //Collection<Train> trains = transportation.lookupTrain("R634");

    }
}
