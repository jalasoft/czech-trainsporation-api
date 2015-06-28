package cz.jalasoft.transportation.czechrailway.impl;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.net.http.HttpGetRequest;
import cz.jalasoft.net.http.HttpResponse;
import cz.jalasoft.transportation.Transport;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import cz.jalasoft.util.text.Fragment;
import cz.jalasoft.util.text.FragmentList;
import cz.jalasoft.util.text.RegexFragment;
import cz.jalasoft.util.text.TextFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    Transport lookupTrain(String trainNumber) throws TransportRetrievalException {
        String foundTrainsPage = searchTrainByNumber(trainNumber);

        LOGGER.debug("Found trains page retrieved.");

        assertTrainExists(trainNumber, foundTrainsPage);
        assertTrainsFoundPageNotMalformed(trainNumber, foundTrainsPage);

        String trainInfoPage = followTrainInfoPage(foundTrainsPage);

        LOGGER.debug("Train info page retrieved.");

        Transport train = readTrainId(trainInfoPage);

        LOGGER.debug("Train found: {}", train);

        return train;
    }

    private String searchTrainByNumber(String trainNumber) throws TransportRetrievalException {
        HttpResponse response = httpClient
                .postRequest()
                //.to() TODO
                .withFormParametersPayload(trainLookupRequestPayload(trainNumber))
                .send();

        if (!response.isStatusOK()) {
            throw new TransportRetrievalException(trainNumber, "An error occurred during discovering a train " + trainNumber + ": " + response.getStatusCode() + ": " + response.getReason());
        }

        return response.getContentAsString();
    }

    private void assertTrainExists(String trainNumber, String foundTrainsPage) throws TransportRetrievalException {
        TextFragment fragment = Fragment.fromText(foundTrainsPage);

        boolean trainNotFound = !fragment.findFragmentsMatching("Vlak nebyl nalezen.").isEmpty();

        if (trainNotFound) {
            String message = "Train " + trainNumber + " not found";
            throw new TransportRetrievalException(trainNumber, message);
        }
    }

    private void assertTrainsFoundPageNotMalformed(String trainNumber, String foundTrainsPage) throws TransportRetrievalException {
        TextFragment fragment = Fragment.fromText(foundTrainsPage);
        FragmentList<RegexFragment> links = fragment.findFragmentsMatching("<a href=\"(.*)\" class=\"train thin\">");

        if (links.isEmpty()) {
            String message = "No hyperlinks found leading to a train info.";
            throw new TransportRetrievalException(trainNumber, message);
        }
    }

    private String followTrainInfoPage(String foundTrainsPage) {
        TextFragment fragment = Fragment.fromText(foundTrainsPage);
        FragmentList<RegexFragment> links = fragment.findFragmentsMatching("<a href=\"(.*)\" class=\"train thin\">");

        RegexFragment link = links.first();


        HttpGetRequest request = httpClient.getRequest();
        //TODO


        //TODO oddelit host od parametru
        //TextFragment trainDetailPath = fragments.first().getGroupTextFragment(0);

        //return request;
        return null;
    }

    private String trainLookupRequestPayload(String trainNumber) {
        String today = new SimpleDateFormat(DATE_PATTERN).format(new Date());
        return String.format(TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN, trainNumber, today);
    }

    private Transport readTrainId(String trainInfoPage) {
        return null;
    }
}
