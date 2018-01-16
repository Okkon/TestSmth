package logic;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractFilter<T> implements GFilter<T> {
    @Override
    public boolean check(T obj) {
        final boolean ok = isOk(obj);
        if (!ok) {
//            model.error(errorText);
        }
        return ok;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void filter(Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (!isOk(next)) {
                iterator.remove();
            }
        }
    }


}
