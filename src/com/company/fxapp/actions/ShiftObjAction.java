package com.company.fxapp.actions;

import com.company.fxapp.core.*;
import com.company.fxapp.events.ShiftUnitEvent;

public class ShiftObjAction<T extends PlaceHaving> extends AbstractAction<T> {

    @Override
    public void init() {
        aimType = AimType.Object;
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
}
