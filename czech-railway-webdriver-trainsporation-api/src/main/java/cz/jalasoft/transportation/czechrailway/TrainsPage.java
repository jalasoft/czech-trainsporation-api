package cz.jalasoft.transportation.czechrailway;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 10/4/15.
 */
final class TrainsPage implements TrainSearchResult {

    @Override
    public TrainSearchResultType type() {
        return TrainSearchResultType.SEVERAL_FOUND;
    }
}
