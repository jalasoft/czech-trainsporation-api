package cz.jalasoft.transportation.alternative.czechrailway;

import java.time.LocalDateTime;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-25.
 */
final class TrainBuilder {

    String code;
    LocalDateTime recordTime;

    TrainBuilder code(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Train code must not be null or empty.");
        }

        this.code = code;
        return this;
    }

    TrainBuilder recordedOn(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("Request time must not be null.");
        }

        this.recordTime = time;
        return this;
    }

    Train get() {
        return new Train(this);
    }
}
