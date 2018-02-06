package logic;

import logic.attack.AttackProps;
import logic.skills.MoveSkill;

import java.util.List;

public class GUnit extends GObj {
    private static int totalCount = 0;
    private final int id;
    private UnitType unitType;
    private int currentHp;
    private int currentMp;
    private int maxHp;
    private int maxMp;
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
        MoveSkill moveSkill = MoveSkill.getInstance();
        moveSkill.setActor(this);
        return moveSkill;
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

    public int getMaxHp() {
        return maxHp > 0 ? maxHp : getType().getMaxHp();
    }

    public int getMaxMp() {
        return maxMp > 0 ? maxMp : getType().getMaxMp();
    }
}
