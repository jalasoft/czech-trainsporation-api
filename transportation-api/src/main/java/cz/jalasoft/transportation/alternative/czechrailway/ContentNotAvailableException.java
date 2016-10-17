package cz.jalasoft.transportation.alternative.czechrailway;

import cz.jalasoft.transportation.alternative.TransportLookupException;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-07.
 */
public final class ContentNotAvailableException extends TransportLookupException {

    public ContentNotAvailableException(String reason) {
        super(reason);
    }

    public ContentNotAvailableException(String reason, Exception exc) {
        super(reason, exc);
    }
}
