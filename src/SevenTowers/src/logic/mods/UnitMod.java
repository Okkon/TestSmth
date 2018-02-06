package logic.mods;


import logic.GEvent;
import logic.GMod;

public abstract class UnitMod<T extends GEvent> extends GMod<T> {
    protected final int value;

    public UnitMod(int value, Class eventClass) {
        super(eventClass, false);
        this.value = value;
    }
}
