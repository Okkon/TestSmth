package com.company.fxapp.core;

import java.util.Collection;

public interface GFilter<T extends PlaceHaving> {
    boolean check(T obj);

    void filter(Collection<T> collection);
}
