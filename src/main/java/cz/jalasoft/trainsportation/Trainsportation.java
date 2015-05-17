package cz.jalasoft.trainsportation;

import com.google.common.base.Optional;

import java.io.IOException;

/**
 * Created by honzales on 3.5.15.
 */
public interface Trainsportation {

    Optional<Train> lookupTrain(String number) throws IOException;

    TrainPosition queryPosition(Train train);

    TrainPosition queryPosition(TrainNumber trainNumber);
}
