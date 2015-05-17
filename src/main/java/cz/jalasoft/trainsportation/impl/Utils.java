package cz.jalasoft.trainsportation.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by honzales on 17.5.15.
 */
final class Utils {

    private static final String DATE_PATTERN = "dd.MM.YYYY";
    private static final String TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN = "Mask=%s&form-date=%s&cmdSearch=Vyhledat";

    static String trainLookupRequestPayload(String trainNumber) {
        String today = new SimpleDateFormat(DATE_PATTERN).format(new Date());
        return String.format(TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN, trainNumber, today);
    }
}
