package cz.jalasoft.transportation.czechrailway.page;

import cz.jalasoft.transportation.czechrailway.page.assertion.ContentAssertion;
import cz.jalasoft.transportation.czechrailway.page.extraction.ValueExtractor;
import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 1.7.15.
 */
public abstract class Page {

    private final TextFragment content;

    public Page(TextFragment content) {
        this.content = content;
    }

    protected final TextFragment content() {
        return content;
    }

    protected final <T> T extract(ValueExtractor<T> extractor) {
        return extractor.extract(content());
    }

    protected final boolean assertThat(ContentAssertion assertion) {
        return assertion.assertContent(content());
    }
}
