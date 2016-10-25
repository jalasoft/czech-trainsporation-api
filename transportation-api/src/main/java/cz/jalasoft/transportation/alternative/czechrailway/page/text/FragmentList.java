package cz.jalasoft.transportation.alternative.czechrailway.page.text;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by honzales on 18.4.15.
 */
public final class FragmentList<T extends Fragment> implements Iterable<T> {

    private final List<T> collection;

    FragmentList(List<T> collection) {
        this.collection = collection;
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    public T first() {
        if (collection.isEmpty()) {
            throw new IllegalStateException("Fragment list is empty.");
        }
        return collection.get(0);
    }

    public Stream<T> stream() {
        return collection.stream();
    }

    public Collection<String> toList() {
        return stream().map(Fragment::text).collect(Collectors.toSet());
    }

    public boolean hasJustOneFragment() {
        return size() == 1;
    }

    public int size() {
        return collection.size();
    }

    public T get(int index) {
        return collection.get(index);
    }
}
