package com.company.fxapp.core;

public abstract class Skill<T extends PlaceHaving> extends AbstractAction<T> {
    private GUnit actor;

    public GUnit getActor() {
        return actor;
    }

    public void setActor(GUnit actor) {
        this.actor = actor;
    }
}
