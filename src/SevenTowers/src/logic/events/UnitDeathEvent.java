package logic.events;

import logic.AbstractEvent;
import logic.GBoard;
import logic.GUnit;
import logic.attack.AttackProps;

public class UnitDeathEvent extends AbstractEvent {
    private final GUnit unit;
    private final AttackProps attackProps;

    public UnitDeathEvent(GUnit unit, AttackProps attackProps) {
        this.unit = unit;
        this.attackProps = attackProps;
    }

    @Override
    protected void perform() {
        unit.getPlace().setObj(null);
        GBoard.getInstance().removeUnit(unit);
    }

    @Override
    public String toString() {
        return String.format("%s dies. Killer is %s", unit, attackProps.getAttacker());
    }

    public GUnit getUnit() {
        return unit;
    }


}
