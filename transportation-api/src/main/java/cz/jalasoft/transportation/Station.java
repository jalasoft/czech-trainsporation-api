package cz.jalasoft.transportation;

import static cz.jalasoft.lifeconfig.util.ArgumentAssertion.mustNotBeNull;
import static cz.jalasoft.lifeconfig.util.ArgumentAssertion.mustNotBeNullOrEmpty;

import java.time.LocalTime;

/**
 * Created by honzales on 28.7.15.
 */
public final class Station {

    private final String name;
    private final LocalTime arrivalTime;
    private final LocalTime departureTime;

    public Station(String name, LocalTime arrivalTime, LocalTime departureTime) {
        mustNotBeNullOrEmpty(name, "Station name");
        mustNotBeNull(arrivalTime, "ArrivalTime");
        mustNotBeNull(departureTime, "Departure time");

        this.name = name;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public String name() {
        return name;
    }

    public LocalTime arrivalTime() {
       return arrivalTime;
    }

    public LocalTime departureTime() {
        return departureTime;
    }
}
