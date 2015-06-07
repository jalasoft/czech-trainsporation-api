package cz.jalasoft.trainsportation.exception;

/**
 * Created by honzales on 7.6.15.
 */
public final class MalformedTrainInfoException extends Exception {

    private final String trainNumber;

    public MalformedTrainInfoException(String trainNumber, String message) {
        super(message);

        this.trainNumber = trainNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}
