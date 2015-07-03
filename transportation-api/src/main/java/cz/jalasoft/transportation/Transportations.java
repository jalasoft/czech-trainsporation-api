package cz.jalasoft.transportation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import static cz.jalasoft.transportation.TransportationLoader.loadTransporations;
import static cz.jalasoft.util.ArgumentAssertion.mustNotBeNull;
import static cz.jalasoft.util.ArgumentAssertion.mustNotBeNullOrEmpty;

/**
 * Created by Honza Lastovicka on 27.6.15.
 */
public final class Transportations {

    private static Map<Carrier, Transportation> transportations = loadTransporations();

    public static Carrier lookupCarrier(String carrierName) {
        mustNotBeNullOrEmpty(carrierName, "Name of carrier");

        for(Carrier carrier : transportations.keySet()) {
            if (carrierName.equals(carrier.name())) {
                return carrier;
            }
        }

        throw new IllegalArgumentException("Unknown carrier name '" + carrierName + "'.");
    }

    public static Transportation transportation(Carrier carrier) {
        mustNotBeNull(carrier, "Carrier");

        Transportation result = transportations.get(carrier);

        if (result == null) {
            throw new IllegalArgumentException("No transportation exists for carrier " + carrier);
        }
        return result;
    }

    public static Collection<Carrier> availableCarriers() {
        return new HashSet<>(transportations.keySet());
    }
}
