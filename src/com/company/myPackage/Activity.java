package com.company.myPackage;

/**
 * Created by olko1016 on 11/22/2016.
 */
public abstract class Activity {
    protected GObject owner;

    public void setOwner(GObject owner) {
        this.owner = owner;
    }

    public abstract void step();

    public GObject getOwner() {
        return owner;
    }
}
