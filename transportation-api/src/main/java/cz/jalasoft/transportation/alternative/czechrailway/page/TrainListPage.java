package cz.jalasoft.transportation.alternative.czechrailway.page;

import cz.jalasoft.transportation.alternative.czechrailway.ContentNotFoundException;
import cz.jalasoft.transportation.alternative.czechrailway.page.text.Fragment;
import cz.jalasoft.transportation.alternative.czechrailway.page.text.FragmentList;
import cz.jalasoft.transportation.alternative.czechrailway.page.text.TextFragment;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-25.
 */
public final class TrainListPage extends Page {

    private static final String LOOKUP_TRAINS_BOUNDARY_START = "<div id=\"header\">Nalezené vlaky</div>";
    private static final String LOOKUP_TRAINS_BOUNDARY_END = "<p class=\"center changeinput\">";
    private static final String LOOKUP_TRAIN_BOUNDARY_START = "<a href=\"";
    private static final String LOOKUP_TRAIN_BOUNDARY_END = "\" class=\"train thin\"";

    TrainListPage(String content) {
        super(content);
    }

    public Collection<String> trainPaths() throws ContentNotFoundException {
        TextFragment pageFragment = Fragment.fromText(content());

        FragmentList<TextFragment> foundTrainsFragments = pageFragment.findFragmentsBetween(LOOKUP_TRAINS_BOUNDARY_START, LOOKUP_TRAINS_BOUNDARY_END);

        if (foundTrainsFragments.isEmpty()) {
            return Collections.emptyList();
        }

        if (!foundTrainsFragments.hasJustOneFragment()) {
            throw new ContentNotFoundException(this, LOOKUP_TRAINS_BOUNDARY_START, LOOKUP_TRAINS_BOUNDARY_END, "Train links");
        }

        TextFragment foundTrainsFragment = foundTrainsFragments.first();

        FragmentList<TextFragment> trainsFragment = foundTrainsFragment.findFragmentsBetween(LOOKUP_TRAIN_BOUNDARY_START, LOOKUP_TRAIN_BOUNDARY_END);
        return trainsFragment.toList();
    }
}
