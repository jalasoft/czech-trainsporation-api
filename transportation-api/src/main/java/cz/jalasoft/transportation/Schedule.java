package cz.jalasoft.transportation;

import java.util.function.Consumer;

/**
 * Created by honzales on 3.5.15.
 */
public interface Schedule {

    Station departureStation();
    Station terminusStation();

    void foreachStation(Consumer<Station> station);
}
