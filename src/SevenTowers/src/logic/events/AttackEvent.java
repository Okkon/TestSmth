package logic.events;

import logic.AbstractEvent;
import logic.GUnit;
import logic.PlaceHaving;
import logic.attack.AttackProps;

public class AttackEvent extends AbstractEvent {
    private final GUnit actor;
    private final PlaceHaving aim;
    private final AttackProps attackProps;

    public <T extends PlaceHaving> AttackEvent(GUnit actor, T aim, AttackProps attackProps) {
        this.actor = actor;
        this.aim = aim;
        this.attackProps = attackProps;
        this.attackProps.setAttacker(actor);
    }

    @Override
    public String toString() {
        return String.format("%s attack %s with base attack(%s)", actor, aim, attackProps);
    }

    @Override
    protected void perform() {
        new StrikeEvent(actor, aim, attackProps).process();
    }
}
