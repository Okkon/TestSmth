package logic.mods;

import logic.GEvent;
import logic.GMod;
import logic.GObj;


public class Armor<T extends GEvent, H extends GObj> extends GMod<T, H> {
    private final int value;

    public Armor(int value) {
        this.value = value;
    }

    @Override
    public void doBeforeEvent(T event) {
        super.doBeforeEvent(event);
    }

    @Override
    public String toString() {
        return String.format("Armor(%s)", value);
    }
}
