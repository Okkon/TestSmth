package com.company.fxapp.actions;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.AimType;
import com.company.fxapp.core.GUnit;
import com.company.fxapp.core.GameCell;
import com.company.fxapp.events.CreateUnitEvent;

public class CreateUnitAction<T extends GameCell> extends AbstractAction<T> {

    @Override
    protected void setAimFilters() {
        aimType = AimType.Cell;
    }

    @Override
    public void doAction() {
        new CreateUnitEvent((getAim()), new GUnit()).process();
    }
}
