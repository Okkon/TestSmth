package com.company.fxapp.core;

import java.util.List;

public interface GAction<T> {
    void cancel();

    void onSelect();

    void perform();

    List<T> findPossibleAims();

    void tryToSelect(T obj);
}
