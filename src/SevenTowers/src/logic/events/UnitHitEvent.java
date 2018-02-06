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
        processInnerEvent(new UnitLoseHpEvent(aim, attackProps));
    }

    @Override
    public String toString() {
        return String.format("%s takes hit with Damage = %s", aim, attackProps.getTotalDamage());
    }

    public AttackProps getAttackProps() {
        return attackProps;
    }

    public GUnit getAim() {
        return aim;
    }
}
