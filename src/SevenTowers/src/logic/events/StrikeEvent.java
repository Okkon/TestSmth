package logic.events;

import logic.AbstractEvent;
import logic.GUnit;
import logic.PlaceHaving;
import logic.attack.AttackProps;

public class StrikeEvent extends AbstractEvent {
    private final GUnit actor;
    private final PlaceHaving aim;
    private final AttackProps attackProps;

    public <T extends PlaceHaving> StrikeEvent(GUnit actor, T aim, AttackProps attackProps) {
        this.actor = actor;
        this.aim = aim;
        this.attackProps = attackProps;
    }

    @Override
    protected void perform() {
        if (aim instanceof GUnit) {
            GUnit unit = (GUnit) aim;
            attackProps.setAim(unit);
            attackProps.generateHit();
            processInnerEvent(new UnitHitEvent(unit, attackProps));
        }
    }

    @Override
    public String toString() {
        return String.format("%s strikes on %s. Damage = %s", actor, aim, attackProps);
    }
}
