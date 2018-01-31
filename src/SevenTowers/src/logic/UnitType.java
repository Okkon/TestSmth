package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnitType {
    private String typeName;
    private int maxHp;
    private int maxMp;
    private List<Skill> skills;
    private List<GMod> mods = new ArrayList<>();

    public UnitType(String typeName, int maxMp, int maxHp, List<Skill> skills, GMod... mods) {
        this.typeName = typeName;
        this.maxMp = maxMp;
        this.maxHp = maxHp;
        this.skills = skills;
        Collections.addAll(this.mods, mods);
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public List<GMod> getMods() {
        return mods;
    }

    public List<Skill> getSkills() {
        return skills;
    }
}
