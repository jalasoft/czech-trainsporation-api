package cz.jalasoft.trainsportation;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.net.http.HttpGetRequest;
import cz.jalasoft.net.http.HttpResponse;
import cz.jalasoft.trainsportation.exception.MalformedTrainInfoException;
import cz.jalasoft.trainsportation.exception.TrainNotFoundException;
import cz.jalasoft.util.text.Fragment;
import cz.jalasoft.util.text.FragmentList;
import cz.jalasoft.util.text.RegexFragment;
import cz.jalasoft.util.text.TextFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Honza Lastovicka on 7.6.15.
 */
final class TrainLookup {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainLookup.class);

    private static final String DATE_PATTERN = "dd.MM.YYYY";
    private static final String TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN = "Mask=%s&form-date=%s&cmdSearch=Vyhledat";

    private final HttpClient httpClient;

    TrainLookup(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    TrainId lookupTrain(String trainNumber) throws IOException, TrainNotFoundException, MalformedTrainInfoException {
        String foundTrainsPage = searchTrainByNumber(trainNumber);

        LOGGER.debug("Found trains page retrieved.");

        assertTrainExists(trainNumber, foundTrainsPage);
        assertTrainsFoundPageNotMalformed(trainNumber, foundTrainsPage);

        String trainInfoPage = followTrainInfoPage(foundTrainsPage);

        LOGGER.debug("Train info page retrieved.");

        TrainId train = readTrainId(trainInfoPage);

        LOGGER.debug("Train found: {}", train);

        return train;
    }

    private String searchTrainByNumber(String trainNumber) throws IOException {
        HttpResponse response = httpClient
                .postRequest()
                .to()
                .withFormParametersPayload(trainLookupRequestPayload(trainNumber))
                .send();

        if (!response.isStatusOK()) {
            throw new IOException("An error occurred during discovering a train " + trainNumber + ": " + response.getStatusCode() + ": " + response.getReason());
        }

        return response.getContentAsString();
    }

    private void assertTrainExists(String trainNumber, String foundTrainsPage) throws TrainNotFoundException {
        TextFragment fragment = Fragment.fromText(foundTrainsPage);

        boolean trainNotFound = !fragment.findFragmentsMatching("Vlak nebyl nalezen.").isEmpty();

        if (trainNotFound) {
            throw new TrainNotFoundException(trainNumber);
        }
    }

    private void assertTrainsFoundPageNotMalformed(String trainNumber, String foundTrainsPage) throws MalformedTrainInfoException {
        TextFragment fragment = Fragment.fromText(foundTrainsPage);
        FragmentList<RegexFragment> links = fragment.findFragmentsMatching("<a href=\"(.*)\" class=\"train thin\">");

        if (links.isEmpty()) {
            throw new MalformedTrainInfoException(trainNumber, "No hyperlinks found leading to a train info.");
        }
    }

    private String followTrainInfoPage(String foundTrainsPage) {
        TextFragment fragment = Fragment.fromText(foundTrainsPage);
        FragmentList<RegexFragment> links = fragment.findFragmentsMatching("<a href=\"(.*)\" class=\"train thin\">");

        RegexFragment link = links.first();


        HttpGetRequest request = httpClient.getRequest();
        //TODO


        //TODO oddelit host od parametru
        TextFragment trainDetailPath = fragments.first().getGroupTextFragment(0);

        return request;
    }

    private String trainLookupRequestPayload(String trainNumber) {
        String today = new SimpleDateFormat(DATE_PATTERN).format(new Date());
        return String.format(TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN, trainNumber, today);
    }

    private TrainId readTrainId(String trainInfoPage) {
        return null;
    }
}
