package com.company.fxapp.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractEvent implements GEvent {
    private static final Map<Class, List<GEventListener<GEvent>>> listenersMap = new HashMap<>();
    private static final List<GEventListener<GEvent>> superListeners = new ArrayList<>();
    private boolean aborted = false;

    public final void process() {
        doBeforeEvent();
        if (!canBePerformed()) {
            return;
        }
        perform();
        doAfterEvent();
    }

    private boolean canBePerformed() {
        return !aborted;
    }

    protected void doBeforeEvent() {
        for (GEventListener<GEvent> superListener : superListeners) {
            superListener.doBeforeEvent(this);
        }
        List<GEventListener<GEvent>> listenerList = listenersMap.get(getClass());
        if (listenerList != null) {
            for (GEventListener<GEvent> listener : listenerList) {
                listener.doBeforeEvent(this);
            }
        }
    }

    protected void doAfterEvent() {
        for (GEventListener<GEvent> superListener : superListeners) {
            superListener.doAfterEvent(this);
        }
        List<GEventListener<GEvent>> listenerList = listenersMap.get(getClass());
        if (listenerList != null) {
            for (GEventListener<GEvent> listener : listenerList) {
                listener.doAfterEvent(this);
            }
        }
    }

    protected abstract void perform();

    public static void addListener(Class<? extends GEvent> eventClass, GEventListener listener) {
        List<GEventListener<GEvent>> listenerList = listenersMap.get(eventClass);
        if (listenerList == null) {
            listenerList = new ArrayList<>();
            listenerList.add(listener);
            listenersMap.put(eventClass, listenerList);
        } else {
            listenerList.add(listener);
        }
    }

    public static void removeListener(Class<? extends GEvent> eventClass, GEventListener listener) {
        List<GEventListener<GEvent>> listenerList = listenersMap.get(eventClass);
        if (listenerList != null) {
            listenerList.remove(listener);
        }
    }

    public static void addSuperListener(GEventListener<GEvent> listener) {
        superListeners.add(listener);
    }
}
