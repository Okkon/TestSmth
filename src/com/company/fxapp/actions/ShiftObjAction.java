package com.company.fxapp.actions;

import com.company.fxapp.core.*;
import com.company.fxapp.events.ShiftUnitEvent;
import com.company.fxapp.filters.VacantCellFilter;

public class ShiftObjAction<T extends PlaceHaving> extends AbstractAction<T> {
    private static ShiftObjAction INSTANCE = new ShiftObjAction();

    private ShiftObjAction() {
    }
    public static ShiftObjAction getInstance() {
        return INSTANCE;
    }

    @Override
    public void doAction() {
        final GameCell place = (GameCell) getAims().get(1);
        final GObj obj = (GObj) getAims().get(0);
        new ShiftUnitEvent(place, obj).process();
    }

    @Override
    protected boolean allAimsSelected() {
        return aims.size() == 2;
    }

    @Override
    protected void setAimFilters() {
        if (aims.isEmpty()) {
            aimType = AimType.Object;
        } else {
            aimType = AimType.Cell;
            addAimFilter(VacantCellFilter.getInstance());
        }
    }
}
