package com.company.fxapp.core;

public interface GFilter<T extends PlaceHaving> {
    boolean check(T obj);
}
