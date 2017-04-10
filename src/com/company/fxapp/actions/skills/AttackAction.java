package com.company.fxapp.actions.skills;

import com.company.fxapp.core.AimType;
import com.company.fxapp.core.AttackProps;
import com.company.fxapp.core.PlaceHaving;
import com.company.fxapp.core.Skill;
import com.company.fxapp.events.AttackEvent;
import com.company.fxapp.events.StrikeEvent;
import com.company.fxapp.filters.IsNearFilter;

public class AttackAction<T extends PlaceHaving> extends Skill<T> {
    private AttackProps attackProps;

    @Override
    protected void setAimFilters() {
        aimType = AimType.Object;
        addAimFilter(IsNearFilter.getInstance().setCell(getActor().getPlace()));
    }

    @Override
    public void doAction() {
        new AttackEvent(getActor(), getAim(), this).process();
    }


    public void attack() {
        new StrikeEvent(getActor(), getAim(), attackProps).process();
    }
}
