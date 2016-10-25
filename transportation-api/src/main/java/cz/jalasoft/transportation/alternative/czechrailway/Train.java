package cz.jalasoft.transportation.alternative.czechrailway;

import cz.jalasoft.transportation.alternative.Transport;

import java.time.LocalDateTime;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-07-25.
 */
public final class Train implements Transport {

    static TrainBuilder newTrain() {
        return new TrainBuilder();
    }

    //-------------------------------------------------
    //INSTANCE SCOPE
    //-------------------------------------------------

    private final String code;
    private final LocalDateTime recordTime;

    Train(TrainBuilder builder) {
        this.code = builder.code;
        this.recordTime = builder.recordTime;
    }

    @Override
    public String code() {
        return code;
    }

    public LocalDateTime requestedAt() {
        return recordTime;
    }
}
