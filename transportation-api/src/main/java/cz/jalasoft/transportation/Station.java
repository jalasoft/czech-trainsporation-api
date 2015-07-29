package cz.jalasoft.transportation;

import java.time.LocalTime;

/**
 * Created by honzales on 28.7.15.
 */
public interface Station {

    String name();
    LocalTime arrivalTime();
    LocalTime departureTime();
}
