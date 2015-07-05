package cz.jalasoft.transportation.czechrailway.page.extraction;

import java.util.List;

/**
 * Created by honzales on 3.7.15.
 */
public final class ValueExtractors {

    public static ValueExtractor<List<String>> trainsDetailsLinks() {
        return new TrainDetailLinksExtractor();
    }
}
