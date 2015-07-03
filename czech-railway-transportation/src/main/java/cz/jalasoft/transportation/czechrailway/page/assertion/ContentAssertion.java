package cz.jalasoft.transportation.czechrailway.page.assertion;

import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 3.7.15.
 */
public interface ContentAssertion {

    boolean assertContent(TextFragment content);
}
