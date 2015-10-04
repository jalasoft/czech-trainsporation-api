package cz.jalasoft.transportation.czechrailway;

import org.openqa.selenium.WebDriver;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
public abstract class Page {

    private final WebDriver driver;

    protected Page(WebDriver driver) {
        this.driver = driver;
    }

    public final WebDriver driver() {
        return driver;
    }
}
