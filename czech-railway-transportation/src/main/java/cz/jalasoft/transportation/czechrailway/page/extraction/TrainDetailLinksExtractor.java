package cz.jalasoft.transportation.czechrailway.page.extraction;

import cz.jalasoft.util.text.FragmentList;
import cz.jalasoft.util.text.RegexFragment;
import cz.jalasoft.util.text.TextFragment;

import java.util.Collections;
import java.util.List;

/**
 * Created by honzales on 5.7.15.
 */
final class TrainDetailLinksExtractor implements ValueExtractor<List<String>> {

    private static final String TRAIN_LINK_PATTERN = "<a href=\".*\" class=\"train thin\">";

   // "<a href=\"/vlak/detail/?p=MCUxNzM5NDQlLTElLTElLTElJSU1LiA3LiAyMDE1IDA6MDA6MDAlVmxhay04M2M1OTA5NDdiNTY0M2FhYmQxOWIwYTg1NWZjMGM5OS02
   //         &amp;uit=ODYzJTUuIDcuIDIwMTUgMDowMDowMCVWbGFrLTgzYzU5MDk0N2I1NjQzYWFiZDE5YjBhODU1ZmMwYzk5LTYlLTEvMTczOTY3JTA-\" class=\"train thin\">";

    @Override
    public List<String> extract(TextFragment fragment) {
        FragmentList<RegexFragment> links = fragment.findFragmentsMatching(TRAIN_LINK_PATTERN);

        return Collections.emptyList();
    }
}
