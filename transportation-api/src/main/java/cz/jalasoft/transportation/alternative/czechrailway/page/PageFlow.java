package cz.jalasoft.transportation.alternative.czechrailway.page;

import cz.jalasoft.transportation.alternative.czechrailway.ContentNotFoundException;
import cz.jalasoft.transportation.alternative.czechrailway.TrainLookupConfig;
import cz.jalasoft.transportation.alternative.czechrailway.page.loader.PageLoader;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toSet;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-25.
 */
public final class PageFlow implements Closeable {

    private final PageLoader loader;
    private final TrainLookupConfig config;

    public PageFlow(PageLoader loader, TrainLookupConfig config) {
        this.loader = loader;
        this.config = config;
    }

    public TrainListPage getTrainListPage(String codeOrName) throws IOException {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));

        List<PageLoader.FormParameter> params = new ArrayList<>();
        params.add(new PageLoader.FormParameter("Mask", codeOrName));
        params.add(new PageLoader.FormParameter("form-date", date));
        params.add(new PageLoader.FormParameter("cmdSearch", "Vyhledat"));

        String pageContent = loader.postForm(config.uriString(), params);
        return new TrainListPage(pageContent);
    }

    public Collection<TrainDetailPage> toTrainDetailPage(TrainListPage trainList) throws IOException, ContentNotFoundException {
        Collection<String> trainUrls = trainList.trainPaths()
                .stream()
                .map(path -> "http://" + config.host() + ":" + config.port() + path)
                .collect(toSet());

        List<CompletableFuture<TrainDetailPage>> futures = trainUrls.stream()
                .map(url -> supplyAsync(new TrainDetailSupplier(url, loader)))
                .collect(Collectors.toList());

        Collection<TrainDetailPage> pages = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        return pages;
    }

    @Override
    public void close() throws IOException {
        loader.close();
    }

    //-----------------------------------------------------------------------
    //TRAIN DETAIL LOADER
    //-----------------------------------------------------------------------

    private static final class TrainDetailSupplier implements Supplier<TrainDetailPage> {

        private final String trainUri;
        private final PageLoader loader;

        TrainDetailSupplier(String trainUri, PageLoader loader) {
            this.trainUri = trainUri;
            this.loader = loader;
        }

        @Override
        public TrainDetailPage get() {
            try {
                String pageContent = loader.get(trainUri);
                return new TrainDetailPage(pageContent);
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }
    }
}
