package com.company.fxapp.events;

import com.company.fxapp.core.AbstractEvent;
import com.company.fxapp.core.GBoard;
import com.company.fxapp.core.GObj;
import com.company.fxapp.core.GameCell;

public class CreateUnitEvent extends AbstractEvent {
    private GameCell place;
    private GObj unit;

    public CreateUnitEvent(GameCell place, GObj unit) {
        this.place = place;
        this.unit = unit;
    }

    @Override
    protected void perform() {
        unit.setPlace(place);
        place.setObj(unit);
        GBoard.getInstance().addUnit(unit);
    }

    public GameCell getPlace() {
        return place;
    }

    public GObj getObj() {
        return unit;
    }
}
