package com.company.fxapp.core;

import java.util.Collection;

public interface GFilter<T> {
    boolean check(T obj);

    boolean isOk(T obj);

    void filter(Collection<T> collection);
}
