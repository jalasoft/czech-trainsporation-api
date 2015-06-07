package cz.jalasoft.trainsportation;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.trainsportation.exception.MalformedTrainInfoException;
import cz.jalasoft.trainsportation.exception.TrainNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by honzales on 3.5.15.
 */
public final class Trainsportation {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trainsportation.class);

    private final HttpClient httpClient;

    public Trainsportation(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Train lookupTrain(String trainNumber) throws IOException, TrainNotFoundException, MalformedTrainInfoException {
        return new TrainInfoRequester(httpClient).retrieveTrainInfo(trainNumber);
    }

    public TrainPosition queryTrainPosition(Train train) {
        return null;
    }

    public TrainPosition queryPosition(TrainNumber trainNumber) {
        return null;
    }
}
