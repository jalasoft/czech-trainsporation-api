package cz.jalasoft.transportation.czechrailway;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
enum TrainSearchResultType {
        NOT_FOUND(By.id("Err_Mask")) {
            @Override
            TrainSearchResult page(WebDriver driver) {
                return new TrainNotFoundPage();
            }
        },
        ONE_FOUND(By.xpath("//div[.='Detail vlaku']")) {
            @Override
            TrainSearchResult page(WebDriver driver) {
                return new TrainDetailPage(driver);
            }
        },
        SEVERAL_FOUND(By.xpath("//div[.='Nalezené vlaky']")) {
            @Override
            TrainSearchResult page(WebDriver driver) {
                return new TrainsPage(driver);
            }
        };

        private final By by;

        TrainSearchResultType(By by) {
            this.by = by;
        }

        Optional<TrainSearchResult> tryIdentify(WebDriver driver) {
            try {
                WebElement element = driver.findElement(by);
                return Optional.of(page(driver));
            } catch (NoSuchElementException exc) {
                return Optional.empty();
            }
        }

        abstract TrainSearchResult page(WebDriver driver);

        static TrainSearchResult tryCreate(WebDriver driver) {
            for (TrainSearchResultType factory : TrainSearchResultType.values()) {
                Optional<TrainSearchResult> maybeResult = factory.tryIdentify(driver);
                if (maybeResult.isPresent()) {
                    return maybeResult.get();
                }
            }

            return null;
        }
    }
