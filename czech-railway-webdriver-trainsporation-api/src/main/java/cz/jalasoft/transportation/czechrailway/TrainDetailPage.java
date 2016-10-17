package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.transportation.Transport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
final class TrainDetailPage extends Page implements TrainSearchResult {

    private static final String TRAIN_NAME_PATTERN = "(\\w+\\s\\d+)(\\s+\\((.+)\\))?";

    @FindBy(how = How.XPATH, xpath = "//span[@class='train']/strong")
    @CacheLookup
    private WebElement train;

    @FindBy(how = How.XPATH, xpath = "//a[@href='http://www.cd.cz']")
    @CacheLookup
    private WebElement carrier;

    public TrainDetailPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver(), this);
    }

    @Override
    public TrainSearchResultType type() {
        return TrainSearchResultType.ONE_FOUND;
    }

    Transport train() {
        Pattern p = Pattern.compile(TRAIN_NAME_PATTERN);

        String name = trainName();
        Matcher m = p.matcher(name);

        if (!m.matches()) {
            throw new RuntimeException("Parsed train name does not match expected pattern " + TRAIN_NAME_PATTERN);
        }

        return Transport.newTransport()
                .carrier(CzechRailwayWebdriverTransportation.CARRIER.name())
                .code(m.group(1))
                .name(m.group(3))
                .get();
    }

    private String trainName() {
        return train.getText();
    }

    boolean belongsToCarrier(String carrier) {
        return carrier.equals(carrier());
    }

    private String carrier() {
        String result = carrier.getText();
        return result;
    }
}
