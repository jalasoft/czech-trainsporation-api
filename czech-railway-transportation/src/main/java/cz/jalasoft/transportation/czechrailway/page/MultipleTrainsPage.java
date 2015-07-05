package cz.jalasoft.transportation.czechrailway.page;

import cz.jalasoft.transportation.czechrailway.page.extraction.ValueExtractors;
import cz.jalasoft.util.text.TextFragment;

import java.util.Collection;

/**
 * Created by honzales on 1.7.15.
 */
public final class MultipleTrainsPage extends Page {

    MultipleTrainsPage(TextFragment content) {
        super(content);
    }

    public Collection<String> extractTrainDetailLinks() {
        return extract(ValueExtractors.trainsDetailsLinks());
    }
}
