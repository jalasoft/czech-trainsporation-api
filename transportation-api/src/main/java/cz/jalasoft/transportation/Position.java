package cz.jalasoft.transportation;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by honzales on 3.5.15.
 */
public final class Position {

    private final String stationName;
    private final LocalDateTime time;
    private final Duration delay;

    public Position(String stationName, LocalDateTime time, Duration delay) {
        this.stationName = stationName;
        this.time = time;
        this.delay = delay;
    }

    public String getStationName() {
        return stationName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Duration getDelay() {
        return delay;
    }
}
