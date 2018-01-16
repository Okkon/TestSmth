package logic;

import java.util.List;

public class GUnit extends GObj {
    private UnitType unitType;
    private int currentHp;
    private int currentMp;
    private List<GMod> mods;

    public GUnit(UnitType unitType) {
        super();
        this.unitType = unitType;
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

    public void loseHp(HitDamage damage) {
        currentHp -= damage.getTotalDamage();
        if (currentHp <= 0) {
            currentHp = 0;
        }
    }
}
