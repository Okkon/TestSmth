package logic.events;

import logic.AbstractEvent;
import logic.GUnit;

public class EndTurnEvent extends AbstractEvent {
    private final GUnit unit;


    @Override
    public String toString() {
        return unit + " ends turn";
    }

    public EndTurnEvent(GUnit unit) {
        this.unit = unit;
    }

    @Override
    protected void perform() {
        unit.loseMp(unit.getMp());
    }
}
