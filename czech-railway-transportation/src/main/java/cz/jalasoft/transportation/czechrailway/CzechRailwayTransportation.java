package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.net.http.netty.NettyHttpClient;
import cz.jalasoft.transportation.*;
import cz.jalasoft.transportation.czechrailway.page.TrainDetailPage;
import cz.jalasoft.transportation.exception.TransportInfoRetrievalException;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static cz.jalasoft.util.ArgumentAssertion.mustNotBeNull;
import static cz.jalasoft.util.ArgumentAssertion.mustNotBeNullOrEmpty;

/**
 * Created by Honza Lastovicka on 25.6.15.
 */
public final class CzechRailwayTransportation implements Transportation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CzechRailwayTransportation.class);

    private final Carrier carrier;

    private final TrainDetailPageFlow trainDetailFlow;


    public CzechRailwayTransportation() {
        carrier = new CzechRailwayCarrier();
        HttpClient httpClient = new NettyHttpClient();

        this.trainDetailFlow = new TrainDetailPageFlow(httpClient);
        LOGGER.debug("Czech Railway Transportation has been instantiated");
    }

    @Override
    public Collection<Transport> lookupTransport(String transport) throws TransportRetrievalException {
        mustNotBeNullOrEmpty(transport, "Name of transport");

        LOGGER.debug("Transport is being looked up: {}", transport);

        Collection<TrainDetailPage> page = trainDetailFlow.trainDetail(transport);



        throw new TransportRetrievalException(transport, "Unexpected page format. No possible state of the page has been indicated by analyzing page content.");
    }

    @Override
    public Schedule querySchedule(Transport transport) throws TransportInfoRetrievalException {
        mustNotBeNull(transport, "Transport");

        LOGGER.debug("Scheduler is being queried for transport {}", transport);
        return null;
    }

    @Override
    public Position queryPosition(Transport transport) throws TransportInfoRetrievalException {
        mustNotBeNull(transport, "Transport");

        LOGGER.debug("Position is being queried for transport {}", transport);
        return null;
    }

    @Override
    public Carrier carrier() {
        return carrier;
    }
}
