package com.company.fxapp.events;

import com.company.fxapp.actions.skills.MoveAction;
import com.company.fxapp.core.AbstractEvent;
import com.company.fxapp.core.GObj;
import com.company.fxapp.core.GUnit;
import com.company.fxapp.core.GameCell;

public class MoveUnitEvent extends AbstractEvent {
    private final MoveAction moveAction;
    private GameCell toCell;
    private GameCell fromCell;
    private GUnit unit;

    public MoveUnitEvent(GameCell toCell, GUnit unit) {
        this.unit = unit;
        this.toCell = toCell;
        this.fromCell = unit.getPlace();
        this.moveAction = unit.getMoveAction();
    }

    @Override
    protected void perform() {
        unit.loseMp(moveAction.calculateStepPrice(fromCell, toCell));
        new ShiftUnitEvent(toCell, unit).process();
    }

    public GameCell getToCell() {
        return toCell;
    }

    public GObj getObj() {
        return unit;
    }
}
