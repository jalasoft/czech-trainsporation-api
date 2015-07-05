package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.transportation.czechrailway.page.MultipleTrainsPage;
import cz.jalasoft.transportation.czechrailway.page.TrainDetailPage;
import cz.jalasoft.transportation.czechrailway.page.UnknownTrainPage;
import cz.jalasoft.transportation.czechrailway.request.ContentRetrievalException;
import cz.jalasoft.transportation.czechrailway.request.TrainDetailProvider;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import cz.jalasoft.util.text.TextFragment;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by honzales on 3.7.15.
 */
public class TrainDetailPageFlow {

    private final TrainDetailProvider trainDetailProvider;

    public TrainDetailPageFlow(HttpClient httpClient) {
        this.trainDetailProvider = new TrainDetailProvider(httpClient);
    }

    public Collection<TrainDetailPage> trainDetail(String train) throws TransportRetrievalException {

        UnknownTrainPage unknownPage = loadTrainPage(train);

        if (unknownPage.isTrainNotFound()) {
            return Collections.emptyList();
        }

        if (unknownPage.isTrainDetail()) {
            TrainDetailPage page = unknownPage.asTrainDetailPage();
            return Collections.singleton(page);
        }

        if (unknownPage.isMultipleTrainsFound()) {
            MultipleTrainsPage trainsPage = unknownPage.asMultipleTrainsPage();
            Collection<String> urls = trainsPage.extractTrainDetailLinks();
            System.out.println(urls);
            //TODO
        }
        throw new TransportRetrievalException(train, "Unexpected page found during train detail page retrieval.");
    }

    private UnknownTrainPage loadTrainPage(String train) throws TransportRetrievalException {
        try {
            TextFragment content =  trainDetailProvider.getContent(train);
            return new UnknownTrainPage(content);
        } catch (ContentRetrievalException exc) {
            throw new TransportRetrievalException(train, exc);
        }
    }
}
