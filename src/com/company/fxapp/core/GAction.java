package com.company.fxapp.core;

import java.util.List;

public interface GAction<T extends PlaceHaving> {
    void cancel();

    void perform();

    List<T> getPossibleAims();

    void tryToSelect(T obj);
}
