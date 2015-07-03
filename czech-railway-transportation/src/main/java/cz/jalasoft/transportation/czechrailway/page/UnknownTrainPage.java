package cz.jalasoft.transportation.czechrailway.page;

import cz.jalasoft.util.text.TextFragment;

import static cz.jalasoft.transportation.czechrailway.page.assertion.ContentAssertions.*;

/**
 * Created by honzales on 3.7.15.
 */
public final class UnknownTrainPage extends Page {

    public UnknownTrainPage(TextFragment content) {
        super(content);
    }

    public boolean isTrainNotFound() {
        return assertThat(trainNotFound());
    }

    public boolean isTrainDetail() {
        return assertThat(trainFound());
    }

    public TrainDetailPage asTrainDetailPage() {
        return new TrainDetailPage(content());
    }

    public boolean isMultipleTrainsFound() {
        return assertThat(multipleTrainsFound());
    }

    public MultipleTrainsPage asMultipleTrainsPage() {
        return new MultipleTrainsPage(content());
    }
}
