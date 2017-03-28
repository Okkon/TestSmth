package com.company.fxapp.actions;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.AimType;
import com.company.fxapp.core.GObj;
import com.company.fxapp.core.GameCell;
import com.company.fxapp.events.CreateUnitEvent;

public class CreateUnitAction<T extends GameCell> extends AbstractAction<T> {

    @Override
    public void init() {
        aimType = AimType.Cell;
    }

    @Override
    public void doAction() {
        new CreateUnitEvent((getAim()), new GObj()).process();
    }
}
