package cz.jalasoft.transportation.czechrailway.page.extraction;

import cz.jalasoft.transportation.czechrailway.assertion.TrainNotFoundAssertion;

/**
 * Created by honzales on 3.7.15.
 */
public class ValueExtractors {

    public static ValueExtractor<Boolean> trainNotFound() {
        return new TrainNotFoundAssertion();
    }
}
