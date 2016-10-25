package cz.jalasoft.transportation.alternative.czechrailway.page;

import cz.jalasoft.transportation.alternative.czechrailway.ContentNotFoundException;

import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-25.
 */
public final class TrainDetailPage extends Page {

    private static final String NO_DELAY_PATTERN = "<span class=\"right bggreen radius ssbox\">bez zpoždění</span>";
    private static final Pattern LAST_UPDATE_TIME_PATTERN = Pattern.compile("<span class=\"textlight\">Aktualizováno (\\d{2}):(\\d{2})</span>");

    public TrainDetailPage(String content) {
        super(content);
    }

    public boolean containsNoDelay() {
        return content().contains(NO_DELAY_PATTERN);
    }

    public Optional<Integer> getDelayMinutes() {
        return null;
    }

    public LocalTime getLastUpdateTime() throws ContentNotFoundException {
        Matcher matcher = LAST_UPDATE_TIME_PATTERN.matcher(content());

        boolean found = matcher.find();

        if (!found) {
            throw new ContentNotFoundException(this, LAST_UPDATE_TIME_PATTERN.pattern());
        }

        int hour = Integer.parseInt(matcher.group(1));
        int minute = Integer.parseInt(matcher.group(2));

        return LocalTime.of(hour, minute);
    }
}
