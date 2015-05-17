package cz.jalasoft.trainsportation.impl;

import com.google.common.base.Optional;
import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.net.http.HttpPostRequest;
import cz.jalasoft.net.http.HttpResponse;
import cz.jalasoft.trainsportation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static cz.jalasoft.trainsportation.impl.UriFactory.*;

/**
 * Created by honzales on 16.5.15.
 */
public class CzechTrainsportation implements Trainsportation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CzechTrainsportation.class);

    private final HttpClient httpClient;

    public CzechTrainsportation(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Optional<Train> lookupTrain(String number) throws IOException {

        String payload = Utils.trainLookupRequestPayload(number);

        HttpPostRequest request = HttpPostRequest
                .to(trainLookupUri())
                .withFormParametersPayload(payload)
                .build();

        HttpResponse response = httpClient.post(request);

        if (!response.isStatusOK()) {
            throw new IOException("An error ocurred during discovering a train " + number + ": " + response.getStatusCode() + ": " + response.getReason());
        }

        System.out.println(response.getContentAsString());


        return Optional.absent();
    }

    @Override
    public TrainPosition queryPosition(Train train) {
        return null;
    }

    @Override
    public TrainPosition queryPosition(TrainNumber trainNumber) {
        return null;
    }
}
