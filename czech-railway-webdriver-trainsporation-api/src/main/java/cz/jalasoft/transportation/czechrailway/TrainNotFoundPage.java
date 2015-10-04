package cz.jalasoft.transportation.czechrailway;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
public class TrainNotFoundPage implements TrainSearchResult {

    @Override
    public TrainSearchResultType type() {
        return TrainSearchResultType.NOT_FOUND;
    }
}
