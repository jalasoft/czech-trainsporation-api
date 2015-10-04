package cz.jalasoft.transportation.czechrailway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
final class SearchTrainPage extends Page {

    private final Configuration configuration;

    @FindBy(how = How.ID, id = "Mask")
    private WebElement trainMaskTextbox;

    @FindBy(how = How.NAME, name = "cmdSearch")
    private WebElement searchButton;

    SearchTrainPage(WebDriver driver, Configuration configuration) {
        super(driver);
        this.configuration = configuration;

        driver().get(this.configuration.trainInfoUrl());

        PageFactory.initElements(driver, this);
    }

    TrainSearchResult search(String trainMask) {
        trainMaskTextbox.sendKeys(trainMask);
        searchButton.click();

        ExpectedCondition<TrainSearchResult> condition = driver -> TrainSearchResultType.tryCreate(driver);
        return new WebDriverWait(driver(), configuration.webTimeout()).until(condition);
    }

}
