package cz.jalasoft.transportation.alternative.czechrailway;

import cz.jalasoft.lifeconfig.LifeConfig;
import cz.jalasoft.transportation.alternative.Position;
import cz.jalasoft.transportation.alternative.Transportation;
import cz.jalasoft.transportation.alternative.czechrailway.page.PageFlow;
import cz.jalasoft.transportation.alternative.czechrailway.page.TrainDetailPage;
import cz.jalasoft.transportation.alternative.czechrailway.page.TrainListPage;
import cz.jalasoft.transportation.alternative.czechrailway.page.loader.ApacheHttpPageLoader;
import cz.jalasoft.transportation.alternative.czechrailway.page.loader.PageLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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

        return new CzechRailwayTransportation(ApacheHttpPageLoader.defaultHttpClient(), config);
    }

    //----------------------------------------------------------------
    //INSTANCE SCOPE
    //----------------------------------------------------------------

    private final TrainLookupConfig config;
    private final PageFlow flow;

    private CzechRailwayTransportation(PageLoader loader, TrainLookupConfig config) {
        this.config = config;
        this.flow = new PageFlow(loader, config);
    }

    @Override
    public Collection<Train> lookup(String codeOrName) throws IOException, ContentNotFoundException {
        if (codeOrName == null || codeOrName.isEmpty()) {
            throw new IllegalArgumentException("Code or name of a train must not be null or empty.");
        }

        TrainListPage trainList = flow.getTrainListPage(codeOrName);
        Collection<TrainDetailPage> trainDetails = flow.toTrainDetailPage(trainList);

        Collection<Train> trains = new ArrayList<>(trainDetails.size());

        for(TrainDetailPage page : trainDetails) {
            trains.add(newTrain(page));
        }
        return trains;
    }

    private Train newTrain(TrainDetailPage page) throws ContentNotFoundException {

        LocalDateTime recordTime = page.getLastUpdateTime().atDate(LocalDate.now());

        return Train.newTrain().recordedOn(recordTime).get();
    }

    @Override
    public Optional<Position> queryPosition(Train transport) {
        return null;
    }

    @Override
    public void close() throws IOException {
        this.flow.close();
    }

    public static void main(String[] args) throws IOException, ContentNotFoundException {
        CzechRailwayTransportation t = CzechRailwayTransportation.czechRailways();
        Collection<Train> trains = t.lookup("Hasek");

    }
}
