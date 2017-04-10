package com.company.fxapp.events;

import com.company.fxapp.core.AbstractEvent;
import com.company.fxapp.core.GUnit;
import com.company.fxapp.core.HitDamage;

public class UnitHitEvent extends AbstractEvent {
    private final GUnit aim;
    private HitDamage hitDamage;

    public UnitHitEvent(GUnit aim, HitDamage hitDamage) {
        this.aim = aim;
        this.hitDamage = hitDamage;
    }

    @Override
    protected void perform() {
        aim.loseHp(hitDamage);
    }
}
