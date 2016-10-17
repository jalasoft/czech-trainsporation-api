package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.*;
import cz.jalasoft.transportation.exception.TransportInfoRetrievalException;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/2/15.
 */
public class CzechRailwayWebdriverTransportation implements Transportation {

    static final Carrier CARRIER = () -> "České dráhy, a.s.";

    private final Configuration config;
    private final WebDriver driver;

    public CzechRailwayWebdriverTransportation() {
        this.config = new Configuration();
        this.driver = new HtmlUnitDriver(true);
    }

    @Override
    public Collection<Transport> findTransport(String transportMask) throws TransportRetrievalException {

        TrainSearchResult result = new SearchTrainPage(driver, config).search(transportMask);

        System.out.println(result.type());
         switch (result.type()) {
            case NOT_FOUND:
                return Collections.emptyList();
            case ONE_FOUND:
                Transport transport =  ((TrainDetailPage) result).train();
                boolean b = ((TrainDetailPage) result).belongsToCarrier(CARRIER.name());

                return Arrays.asList(transport);
             case SEVERAL_FOUND:
                 ((TrainsPage) result).readDetails();
                return null;

            default:
                throw new IllegalStateException("Unexpected type of train search result:: " + result.type());

        }

        //return null;
    }

    @Override
    public Schedule querySchedule(Transport transport) throws TransportInfoRetrievalException {
        return null;
    }

    @Override
    public Position queryPosition(Transport transport) throws TransportInfoRetrievalException {
        return null;
    }

    @Override
    public Carrier carrier() {
        return CARRIER;
    }
}
