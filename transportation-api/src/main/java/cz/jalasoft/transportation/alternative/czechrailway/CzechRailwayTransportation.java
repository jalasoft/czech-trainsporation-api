package cz.jalasoft.transportation.alternative.czechrailway;

import cz.jalasoft.lifeconfig.LifeConfig;
import cz.jalasoft.transportation.alternative.Position;
import cz.jalasoft.transportation.alternative.TransportLookupException;
import cz.jalasoft.transportation.alternative.Transportation;
import cz.jalasoft.transportation.alternative.czechrailway.content.ApacheHttpClientContentProvider;
import cz.jalasoft.transportation.alternative.czechrailway.content.PageContent;
import cz.jalasoft.transportation.alternative.czechrailway.content.ContentProvider;
import cz.jalasoft.transportation.alternative.czechrailway.factory.TrainFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-07-25.
 */
public final class CzechRailwayTransportation implements Transportation<Train> {

    public static CzechRailwayTransportation czechRailways() {

        TrainLookupConfig config = LifeConfig.pretending(TrainLookupConfig.class)
                .hocon()
                .fromClasspath("reference.conf")
                .load();

        return new CzechRailwayTransportation(config.uriString(), ApacheHttpClientContentProvider.defaultHttpClient());
    }

    //----------------------------------------------------------------
    //INSTANCE SCOPE
    //----------------------------------------------------------------

    private final String uri;
    private final ContentProvider contentProvider;
    private final TrainFactory trainFactory;

    private CzechRailwayTransportation(String uri, ContentProvider contetProvider) {
        this.uri = uri;
        this.contentProvider = contetProvider;
        this.trainFactory = new TrainFactory();
    }

    @Override
    public Collection<Train> lookup(String codeOrName) throws TransportLookupException {
        PageContent pageContent = loadPage(codeOrName);
        Collection<Train> trains = trainFactory.createTrain(pageContent);
        return trains;

    }

    private PageContent loadPage(String codeOrName) throws TransportLookupException {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

        List<ContentProvider.FormParameter> params = new ArrayList<>();
        params.add(new ContentProvider.FormParameter("Mask", codeOrName));
        params.add(new ContentProvider.FormParameter("form-date", date));
        params.add(new ContentProvider.FormParameter("cmdSearch", "Vyhledat"));

        try {
            return contentProvider.postForm(uri, params);
        } catch(IOException exc) {
            throw new ContentNotAvailableException("Could not load page.", exc);
        }
    }

    @Override
    public Optional<Position> queryPosition(Train transport) {
        return null;
    }

    @Override
    public void close() throws IOException {
        this.contentProvider.close();
    }

    public static void main(String[] args) throws TransportLookupException {
        CzechRailwayTransportation t = CzechRailwayTransportation.czechRailways();
        Collection<Train> trains = t.lookup("Hasek");

    }
}
