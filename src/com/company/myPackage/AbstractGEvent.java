package com.company.myPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olko1016 on 11/22/2016.
 */
public abstract class AbstractGEvent {
    List<GEventListener> listenerList = new ArrayList<>();

    protected void process() {
        for (GEventListener listener : listenerList) {
            listener.fireBeforeEvent(this);
        }
        performAction();
    }

    protected abstract void performAction();
}
