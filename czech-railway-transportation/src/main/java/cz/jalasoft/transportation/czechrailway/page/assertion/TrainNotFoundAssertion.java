package cz.jalasoft.transportation.czechrailway.page.assertion;

import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 3.7.15.
 */
final class TrainNotFoundAssertion implements ContentAssertion {

    private final String SENTENCE = "Vlak nebyl nalezen.";

    @Override
    public boolean assertContent(TextFragment fragment) {
        boolean trainNotFound = !fragment.findFragmentsMatching(SENTENCE).isEmpty();
        return trainNotFound;
    }
}
