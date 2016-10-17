package cz.jalasoft.transportation.czechrailway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
final class TrainsPage extends Page implements TrainSearchResult {

    @FindBy(how = How.XPATH, xpath = "//a[@href[contains(., 'vlak/detail')]]")
    @CacheLookup
    private Collection<WebElement> trainLinks;

    TrainsPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    public TrainSearchResultType type() {
        return TrainSearchResultType.SEVERAL_FOUND;
    }

    Collection<TrainDetailPage> readDetails() {
        for(WebElement e : trainLinks) {
            System.out.println(e.getText());
        }


        //CompletableFuture.
        return null;
    }
}
