package logic;

import java.util.Collection;

public interface GFilter<T> {
    boolean isOk(T obj);

    void filter(Collection<T> collection);
}
