package cz.jalasoft.trainsportation.exception;

/**
 * Created by honzales on 3.5.15.
 */
public final class TrainNotFoundException extends Exception {

    private final String trainNumber;

    public TrainNotFoundException(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}
