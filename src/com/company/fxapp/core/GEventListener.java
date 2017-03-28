package com.company.fxapp.core;

public interface GEventListener<T extends GEvent> {
    void doBeforeEvent(T event);

    void doAfterEvent(T event);
}
