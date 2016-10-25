package cz.jalasoft.transportation.alternative;

import cz.jalasoft.transportation.alternative.czechrailway.ContentNotFoundException;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-07-25.
 */
public interface Transportation<T extends Transport> extends Closeable {

    Collection<T> lookup(String codeOrName) throws IOException, ContentNotFoundException;

    Optional<Position> queryPosition(T transport) throws TransportQueryException;
}
