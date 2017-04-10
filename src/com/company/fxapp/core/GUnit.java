package com.company.fxapp.core;


import com.company.fxapp.actions.skills.MoveAction;

import java.util.List;

public class GUnit extends GObj {
    private UnitType unitType;
    private int currentHp;
    private int currentMp;
    private List<GMod> mods;
    private MoveAction moveAction;

    public GUnit(UnitType unitType) {
        super();
        this.unitType = unitType;
        this.moveAction = MoveAction.getInstance();
    }

    public GAction getBaseAction() {
        return MoveAction.getInstance();
    }

    public void loseMp(int k) {
        currentMp -= k;
        if (currentMp < 0) {
            currentMp = 0;
        }
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(MoveAction moveAction) {
        this.moveAction = moveAction;
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
