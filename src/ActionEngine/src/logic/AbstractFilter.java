package logic;

import java.util.Iterator;
import java.util.List;

public abstract class AbstractFilter<T> implements GFilter<T> {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public List<T> filter(List<T> collection) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (!isOk(next)) {
                iterator.remove();
            }
        }
        return collection;
    }


}
