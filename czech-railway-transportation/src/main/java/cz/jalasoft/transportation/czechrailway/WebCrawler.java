package cz.jalasoft.transportation.czechrailway;

import cz.jalasoft.net.http.HttpClient;
import cz.jalasoft.transportation.czechrailway.page.UnknownTrainPage;
import cz.jalasoft.transportation.czechrailway.request.ContentRetrievalException;
import cz.jalasoft.transportation.czechrailway.request.TrainDetailProvider;
import cz.jalasoft.transportation.exception.TransportRetrievalException;
import cz.jalasoft.util.text.TextFragment;

/**
 * Created by honzales on 3.7.15.
 */
public class WebCrawler {

    private final TrainDetailProvider trainDetailProvider;

    public WebCrawler(HttpClient httpClient) {
        this.trainDetailProvider = new TrainDetailProvider(httpClient);
    }

    public UnknownTrainPage searchTrain(String train) throws TransportRetrievalException {

        TextFragment content;
        try {
            content = trainDetailProvider.getContent(train);
        } catch (ContentRetrievalException exc) {
            throw new TransportRetrievalException(train, exc);
        }
        return new UnknownTrainPage(content);
    }
}
