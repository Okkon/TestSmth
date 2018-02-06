package logic.events;

import logic.*;

public class StepEvent extends AbstractEvent {
    private final GUnit actor;
    private GameCell fromCell;
    private GameCell toCell;
    private final int stepPrice;

    public <T extends PlaceHaving> StepEvent(GUnit actor, GameCell place, int stepPrice) {
        this.actor = actor;
        this.fromCell = actor.getPlace();
        this.toCell = place;
        this.stepPrice = stepPrice;
    }

    @Override
    public String toString() {
        return String.format("%s step from %s to %s", actor, fromCell, toCell);
    }

    @Override
    protected void perform() {
        if (stepPrice <= actor.getMp()) {
            actor.loseMp(stepPrice);
            processInnerEvent(new ShiftObjectEvent(actor, toCell));
        } else {
            abort();
        }
    }

}
