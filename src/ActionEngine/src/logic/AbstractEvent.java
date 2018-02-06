package logic;

import java.util.*;

public abstract class AbstractEvent implements GEvent {
    private static final Map<Class, List<GEventListener<GEvent>>> listenersMap = new HashMap<>();
    private boolean aborted = false;

    public AbstractEvent() {
        System.out.println(getClass().getSimpleName());
    }

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
                List<GEventListener<GEvent>> listenerList = entry.getValue();
                listenerList.sort(Comparator.comparingDouble(GEventListener::getPriority));
                Iterator<GEventListener<GEvent>> iterator = listenerList.iterator();
                while (iterator.hasNext()) {
                    GEventListener<GEvent> listener = iterator.next();
                    listener.doBeforeEvent(this);
                    if (listener.isToBeRemoved()) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    protected void doAfterEvent() {
        for (Map.Entry<Class, List<GEventListener<GEvent>>> entry : listenersMap.entrySet()) {
            if (entry.getKey().isInstance(this)) {
                List<GEventListener<GEvent>> listenerList = entry.getValue();
                listenerList.sort(Comparator.comparingDouble(GEventListener::getPriority));
                Iterator<GEventListener<GEvent>> iterator = listenerList.iterator();
                while (iterator.hasNext()) {
                    GEventListener<GEvent> listener = iterator.next();
                    listener.doAfterEvent(this);
                    if (listener.isToBeRemoved()) {
                        iterator.remove();
                    }
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

    protected void processInnerEvent(GEvent eventToDo) {
//        eventToDo.process();
        AbstractEvent.addListener(this.getClass(), new GEventListener() {
            @Override
            public void doAfterEvent(GEvent event) {
                eventToDo.process();
            }

            @Override
            public double getPriority() {
                return 10;
            }

            @Override
            public boolean isToBeRemoved() {
                return true;
            }
        });
    }
}
