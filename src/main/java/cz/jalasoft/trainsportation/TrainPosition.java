package cz.jalasoft.trainsportation;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by honzales on 3.5.15.
 */
public final class TrainPosition {

    private final String stationName;
    private final DateTime time;
    private final Duration delay;

    public TrainPosition(String stationName, DateTime time, Duration delay) {
        this.stationName = stationName;
        this.time = time;
        this.delay = delay;
    }

    public String getStationName() {
        return stationName;
    }

    public DateTime getTime() {
        return time;
    }

    public Duration getDelay() {
        return delay;
    }
}
