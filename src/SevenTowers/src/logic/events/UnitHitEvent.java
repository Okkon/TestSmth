package logic.events;

import logic.AbstractEvent;
import logic.GUnit;
import logic.attack.AttackProps;

public class UnitHitEvent extends AbstractEvent {
    private final GUnit aim;
    private final AttackProps attackProps;

    public UnitHitEvent(GUnit aim, AttackProps attackProps) {
        this.aim = aim;
        this.attackProps = attackProps;
    }

    @Override
    protected void perform() {
        new UnitLoseHpEvent(aim, attackProps).process();
    }

    @Override
    public String toString() {
        return String.format("%s takes hit with Damage = %s", aim, attackProps.getTotalDamage());
    }
}
