package cz.jalasoft.transportation.alternative;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-07-26.
 */
public abstract class TransportationException extends Exception {

    protected TransportationException() {

    }

    protected TransportationException(String reason) {
        super(reason);
    }

    protected TransportationException(String reason, Exception cause) {
        super(reason, cause);
    }
}
