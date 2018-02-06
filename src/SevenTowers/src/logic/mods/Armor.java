package logic.mods;

import logic.GMod;
import logic.ModeActivationEvent;
import logic.attack.AttackTypes;
import logic.events.UnitHitEvent;


public class Armor<T extends UnitHitEvent> extends GMod<T> {

    public Armor(int value) {
        super(value, UnitHitEvent.class);
    }

    @Override
    public void doBeforeEvent(T event) {
        if (event.getAim().hasMode(Armor.class)) {
            new ModeActivationEvent(this) {
                @Override
                protected void perform() {
                    event.getAttackProps().reduceDamage(2, AttackTypes.Physic);
                }
            }.process();
        }
    }

    @Override
    public String toString() {
        return String.format("Armor(%s)", value);
    }
}
