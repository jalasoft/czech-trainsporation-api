package cz.jalasoft.transportation.alternative;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-07-26.
 */
public abstract class TransportLookupException extends TransportationException {

    protected TransportLookupException() {

    }

    protected TransportLookupException(String reason) {
        super(reason);
    }

    protected TransportLookupException(String reason, Exception cause) {
        super(reason, cause);
    }
}
