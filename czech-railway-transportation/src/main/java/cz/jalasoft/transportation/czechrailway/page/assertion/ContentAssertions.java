package cz.jalasoft.transportation.czechrailway.page.assertion;

/**
 * Created by honzales on 3.7.15.
 */
public final class ContentAssertions {

    public static ContentAssertion trainNotFound() {
        return new TrainNotFoundAssertion();
    }

    public static ContentAssertion multipleTrainsFound() {
        return new MultipleTrainsFoundAssertion();
    }

    public static ContentAssertion trainFound() {
        return new TrainFoundAssertion();
    }
}
