package cz.jalasoft.transportation.czechrailway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
public class TrainDetailPage extends Page implements TrainSearchResult {

    @FindBy(how = How.XPATH, xpath = "//span[@class='train']/strong")
    private WebElement trainName;

    public TrainDetailPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver(), this);
    }

    @Override
    public TrainSearchResultType type() {
        return TrainSearchResultType.ONE_FOUND;
    }

    public String trainName() {
        return trainName.getText();
    }
}
