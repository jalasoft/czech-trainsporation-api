package cz.jalasoft.transportation.alternative.czechrailway.content;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-06.
 */
public final class PageContent {

    private String content;

    public PageContent(String content) {
        this.content = content;
    }

    public String asString() {
        return content;
    }
}
