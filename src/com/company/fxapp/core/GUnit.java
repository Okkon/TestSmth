package com.company.fxapp.core;

import com.company.fxapp.actions.ShiftObjAction;

import java.util.List;

public class GUnit extends GObj {
    private UnitType unitType;
    private int currentHp;
    private int currentMp;
    private List<GMod> mods;

    public GAction getBaseAction() {
        return ShiftObjAction.getInstance();
    }
}
