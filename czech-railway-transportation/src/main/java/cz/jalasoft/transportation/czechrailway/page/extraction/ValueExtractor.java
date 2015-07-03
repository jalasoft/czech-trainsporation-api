package cz.jalasoft.transportation.czechrailway.page.extraction;

import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 3.7.15.
 */
public interface ValueExtractor<T> {

    T extract(TextFragment fragment);
}
