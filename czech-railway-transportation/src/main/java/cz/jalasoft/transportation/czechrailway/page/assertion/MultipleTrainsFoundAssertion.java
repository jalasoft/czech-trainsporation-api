package cz.jalasoft.transportation.czechrailway.page.assertion;

import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 3.7.15.
 */
final class MultipleTrainsFoundAssertion implements ContentAssertion {

    private static final String PATTERN = "<div id=\"header\">Nalezené vlaky</div>";

    @Override
    public boolean assertContent(TextFragment fragment) {
        return fragment.findFragmentsMatching(PATTERN).hasJustOneFragment();
    }
}
