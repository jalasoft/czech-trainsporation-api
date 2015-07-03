package cz.jalasoft.transportation.czechrailway.page.assertion;

import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 3.7.15.
 */
final class TrainFoundAssertion implements ContentAssertion {

    private static final String PATTERN = "<div id=\"header\">Detail vlaku</div>";

    @Override
    public boolean assertContent(TextFragment content) {
        return content.findFragmentsMatching(PATTERN).hasJustOneFragment();
    }
}
