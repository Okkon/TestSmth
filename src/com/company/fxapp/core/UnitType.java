package com.company.fxapp.core;

import java.util.List;

public class UnitType {
    private int maxHp;
    private int maxMp;
    private List<Skill> skills;
    private List<GMod> mods;

    public UnitType(int maxMp, int maxHp) {
        this.maxMp = maxMp;
        this.maxHp = maxHp;
    }
}
