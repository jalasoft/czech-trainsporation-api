package cz.jalasoft.transportation.alternative.czechrailway.factory;

import cz.jalasoft.transportation.alternative.TransportLookupException;
import cz.jalasoft.transportation.alternative.czechrailway.Train;
import cz.jalasoft.transportation.alternative.czechrailway.content.PageContent;
import cz.jalasoft.transportation.alternative.czechrailway.factory.text.Fragment;
import cz.jalasoft.transportation.alternative.czechrailway.factory.text.FragmentList;
import cz.jalasoft.transportation.alternative.czechrailway.factory.text.TextFragment;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-10.
 */
public final class TrainFactory {

    private static final String FOUND_TRAINS_BOUNDARY_START = "<div id=\"header\">Nalezené vlaky</div>";
    private static final String FOUND_TRAINS_BOUNDARY_END = "<a href=\"/mapa-stranek/\">Mapa stránek</a>";

    public Collection<Train> createTrain(PageContent content) throws TransportLookupException {
        TextFragment pageFragment = Fragment.fromText(content.asString());

        FragmentList<TextFragment> foundTrainsFragments = pageFragment.findFragmentsBetween(FOUND_TRAINS_BOUNDARY_START, FOUND_TRAINS_BOUNDARY_END);

        if (foundTrainsFragments.isEmpty()) {
            return Collections.emptyList();
        }

        if (!foundTrainsFragments.hasJustOneFragment()) {
            throw new TransportPageChangedLookupException(content, FOUND_TRAINS_BOUNDARY_START, FOUND_TRAINS_BOUNDARY_END);
        }

        TextFragment foundTrainsFrafment = foundTrainsFragments.first();

        return Collections.emptyList();
    }
}
