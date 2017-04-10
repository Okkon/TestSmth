package com.company.fxapp.events;

import com.company.fxapp.actions.skills.AttackAction;
import com.company.fxapp.core.AbstractEvent;
import com.company.fxapp.core.GUnit;
import com.company.fxapp.core.PlaceHaving;

public class AttackEvent extends AbstractEvent {
    private final GUnit actor;
    private final PlaceHaving aim;
    private final AttackAction attackAction;

    public <T extends PlaceHaving> AttackEvent(GUnit actor, T aim, AttackAction attackAction) {
        this.actor = actor;
        this.aim = aim;
        this.attackAction = attackAction;
    }

    @Override
    protected void perform() {
        attackAction.attack();
    }
}
