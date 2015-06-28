package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Carrier;

/**
 * Created by honzales on 28.6.15.
 */
public class CzechRailwayCarrier implements Carrier {

    private static final String CZECH_RAILWAY = "Ceske Drahy";

    @Override
    public String name() {
        return CZECH_RAILWAY;
    }
}
