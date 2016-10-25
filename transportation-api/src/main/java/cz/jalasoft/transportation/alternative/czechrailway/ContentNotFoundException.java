package cz.jalasoft.transportation.alternative.czechrailway;

import cz.jalasoft.transportation.alternative.TransportLookupException;
import cz.jalasoft.transportation.alternative.czechrailway.page.Page;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-07.
 */
public final class ContentNotFoundException extends TransportLookupException {

    private final Page page;
    private final String missingFragment;

    public ContentNotFoundException(Page page, String missingFragment) {
        this.page = page;
        this.missingFragment = missingFragment;
    }

    @Override
    public String getMessage() {
        return "Cannot find text fragment in a page page: " + missingFragment;
    }
}
