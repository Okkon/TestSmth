package com.company.fxapp.filters;

import com.company.fxapp.core.AbstractFilter;
import com.company.fxapp.core.GameCell;
import com.company.fxapp.core.PlaceHaving;

public class IsNearFilter<T extends PlaceHaving> extends AbstractFilter<T> {
    private static final IsNearFilter INSTANCE = new IsNearFilter();
    private GameCell cell;

    private IsNearFilter() {
    }

    public static IsNearFilter getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isOk(T aim) {
        return this.cell.isLinkedWith(aim.getPlace());
    }

    public IsNearFilter<T> setCell(GameCell cell) {
        this.cell = cell;
        return this;
    }
}
