package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractEvent implements GEvent {
    private static final Map<Class, List<GEventListener<GEvent>>> listenersMap = new HashMap<>();
    private boolean aborted = false;

    @Override
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
        for (Map.Entry<Class, List<GEventListener<GEvent>>> entry : listenersMap.entrySet()) {
            if (entry.getKey().isInstance(this)) {
                for (GEventListener<GEvent> listener : entry.getValue()) {
                    listener.doBeforeEvent(this);
                }
            }
        }
    }

    protected void doAfterEvent() {
        for (Map.Entry<Class, List<GEventListener<GEvent>>> entry : listenersMap.entrySet()) {
            if (entry.getKey().isInstance(this)) {
                for (GEventListener<GEvent> listener : entry.getValue()) {
                    listener.doAfterEvent(this);
                }
            }
        }
    }

    protected abstract void perform();

    public static void addListener(Class<? extends GEvent> eventClass, GEventListener listener) {
        List<GEventListener<GEvent>> listenerList = listenersMap.get(eventClass);
        if (listenerList == null) {
            listenerList = new ArrayList<>();
            listenersMap.put(eventClass, listenerList);
        }
        listenerList.add(listener);
    }

    public static void removeListener(Class<? extends GEvent> eventClass, GEventListener listener) {
        List<GEventListener<GEvent>> listenerList = listenersMap.get(eventClass);
        if (listenerList != null) {
            listenerList.remove(listener);
        }
    }

    protected void abort() {
        this.aborted = true;
    }

    public static GMod getListener(Class<? extends GEvent> eventClass, Class<? extends GMod> modClass) {
        List<GEventListener<GEvent>> eventListeners = listenersMap.get(eventClass);
        if (eventListeners != null) {
            return (GMod) eventListeners.stream()
                    .filter(modClass::isInstance)
                    .findAny()
                    .orElse(null);
        }
        return null;
    }
}
