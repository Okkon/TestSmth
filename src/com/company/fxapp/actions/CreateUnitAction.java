package com.company.fxapp.actions;

import com.company.fxapp.core.*;
import com.company.fxapp.events.CreateUnitEvent;
import com.company.fxapp.filters.VacantCellFilter;

public class CreateUnitAction<T extends GameCell> extends AbstractAction<T> {

    @Override
    protected void setAimFilters() {
        aimType = AimType.Cell;
        addAimFilter(VacantCellFilter.getInstance());
    }

    @Override
    public void doAction() {
        new CreateUnitEvent((getAim()), new GUnit(new UnitType())).process();
    }
}
