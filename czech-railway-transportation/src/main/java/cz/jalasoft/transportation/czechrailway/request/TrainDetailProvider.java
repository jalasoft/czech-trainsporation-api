package cz.jalasoft.transportation.czechrailway.request;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.net.http.HttpResponse;
import cz.jalasoft.transportation.czechrailway.config.CzechRailwayConnectionInfo;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import cz.jalasoft.util.text.Fragment;
import cz.jalasoft.util.text.TextFragment;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cz.jalasoft.net.http.URIBuilder.http;

/**
 * Created by honzales on 3.7.15.
 */
public final class TrainDetailProvider extends ContentProvider<String> {

    private static final String DATE_PATTERN = "dd.MM.YYYY";
    private static final String TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN = "Mask=%s&form-date=%s&cmdSearch=Vyhledat";

    public TrainDetailProvider(HttpClient client) {
        super(client);
    }

    @Override
    public TextFragment getContent(String train) throws ContentRetrievalException {
        String content = loadPage(train);
        return Fragment.fromText(content);
    }

    private URI uri() {
        return http()
                .host(config().getHost())
                .port(config().getPort())
                .path(config().getTrainInfoPath())
                .build();
    }

    private String loadPage(String trainNumber) throws ContentRetrievalException {
        HttpResponse response = client()
                .postRequest()
                .to(uri())
                .withFormParametersPayload(trainLookupRequestPayload(trainNumber))
                .send();

        if (!response.isStatusOK()) {
            throw new ContentRetrievalException("An error occurred during discovering a train " + trainNumber + ": " + response.getStatusCode() + ": " + response.getReason());
        }

        return response.getContentAsString();
    }

    private String trainLookupRequestPayload(String trainNumber) {
        String today = new SimpleDateFormat(DATE_PATTERN).format(new Date());
        return String.format(TRAIN_LOOKUP_REQUEST_PAYLOAD_PATTERN, trainNumber, today);
    }
}
