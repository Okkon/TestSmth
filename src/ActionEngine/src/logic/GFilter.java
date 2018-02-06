package logic;

import java.util.List;

public interface GFilter<T> {
    boolean isOk(T obj);

    List<T> filter(List<T> collection);
}
