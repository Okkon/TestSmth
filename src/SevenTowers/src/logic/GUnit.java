package logic;

import logic.attack.AttackProps;

import java.util.List;

public class GUnit extends GObj {
    private static int totalCount = 0;
    private final int id;
    private UnitType unitType;
    private int currentHp;
    private int currentMp;
    private List<GMod> mods;

    public GUnit(UnitType unitType) {
        super();
        id = totalCount;
        totalCount++;
        this.unitType = unitType;
        currentHp = unitType.getMaxHp();
        currentMp = unitType.getMaxMp();
        setOwner(Player.NEUTRAL);
    }

    public GAction getBaseAction() {
        ShiftObjAction shiftObjAction = ShiftObjAction.getInstance();
        shiftObjAction.tryToSelect(this);
        return shiftObjAction;
    }

    public int loseMp(int k) {
        int lostMp = Math.min(k, currentMp);
        currentMp -= lostMp;
        return lostMp;
    }

    public int getMp() {
        return currentMp;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%s, %s)", unitType.getTypeName(), id, getPlace());
    }

    public int loseHp(AttackProps damage) {
        int lostHp = Math.min(damage.getTotalDamage(), currentHp);
        currentHp -= lostHp;
        return lostHp;
    }

    public UnitType getType() {
        return unitType;
    }

    public int getHp() {
        return currentHp;
    }
}
