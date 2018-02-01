package logic.events;

import logic.AbstractEvent;
import logic.GUnit;
import logic.attack.AttackProps;

public class UnitLoseHpEvent extends AbstractEvent {
    private final GUnit aim;
    private final AttackProps attackProps;
    private int lostHp;
    private int remainedHp;

    public UnitLoseHpEvent(GUnit aim, AttackProps attackProps) {
        this.aim = aim;
        this.attackProps = attackProps;
    }

    @Override
    protected void perform() {
        lostHp = aim.loseHp(attackProps);
        remainedHp = aim.getHp();
        if (remainedHp < 1) {
            new UnitDeathEvent(aim, attackProps).process();
        }
    }

    @Override
    public String toString() {
        return String.format("%s lose %s HP, %s HP remain", aim, lostHp, remainedHp);
    }

    public GUnit getUnit() {
        return aim;
    }

    public int getRemainedHp() {
        return remainedHp;
    }

}
