package cz.jalasoft.transportation.alternative.czechrailway.page;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-06.
 */
public abstract class Page {

    private String content;

    public Page(String content) {
        this.content = content;
    }

    public String asString() {
        return content;
    }

    final String content() {
        return content;
    }
}
