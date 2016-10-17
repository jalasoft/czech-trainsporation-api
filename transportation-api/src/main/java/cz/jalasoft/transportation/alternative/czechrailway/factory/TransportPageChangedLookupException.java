package cz.jalasoft.transportation.alternative.czechrailway.factory;

import cz.jalasoft.transportation.alternative.TransportLookupException;
import cz.jalasoft.transportation.alternative.czechrailway.content.PageContent;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-17.
 */
public final class TransportPageChangedLookupException extends TransportLookupException {

    private final PageContent content;
    private final String startFragment;
    private final String endFragment;

    public TransportPageChangedLookupException(PageContent content, String startFragment, String endFragment) {
        this.content = content;
        this.startFragment = startFragment;
        this.endFragment = endFragment;
    }
}
