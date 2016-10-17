package cz.jalasoft.transportation.alternative;

import java.io.Closeable;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-07-25.
 */
public interface Transportation<T extends Transport> extends Closeable {

    Collection<T> lookup(String codeOrName) throws TransportLookupException;

    Optional<Position> queryPosition(T transport) throws TransportQueryException;
}
