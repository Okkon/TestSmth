package com.company.fxapp.actions;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.AimType;
import com.company.fxapp.core.GUnit;
import com.company.fxapp.events.ActionSelectionEvent;

public class SelectUnitAction<T extends GUnit> extends AbstractAction<T> {
    private static SelectUnitAction INSTANCE = new SelectUnitAction();

    private SelectUnitAction() {
    }
    public static SelectUnitAction getInstance() {
        return INSTANCE;
    }

    private T selectedObj;

    @Override
    protected void setAimFilters() {
        aimType = AimType.Object;
    }

    @Override
    public void doAction() {
        selectedObj = getAim();
        new ActionSelectionEvent(selectedObj.getBaseAction()).process();
    }
}
