package logic;

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

    public void loseMp(int k) {
        currentMp -= k;
        if (currentMp < 0) {
            currentMp = 0;
        }
    }

    public int getMp() {
        return currentMp;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%s)", unitType.getTypeName(), id);
    }

    public void loseHp(HitDamage damage) {
        currentHp -= damage.getTotalDamage();
        if (currentHp <= 0) {
            currentHp = 0;
        }
    }

    public UnitType getType() {
        return unitType;
    }

    public int getHp() {
        return currentHp;
    }
}
