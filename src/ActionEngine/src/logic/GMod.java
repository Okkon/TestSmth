package logic;

public abstract class GMod<T extends GEvent> implements GEventListener<T> {
    public GMod(Class<? extends GEvent> eventClass, boolean allowDuplicates) {
        register(eventClass, allowDuplicates);
    }

    public void register(Class<? extends GEvent> eventClass, boolean allowDuplicates) {
        if (allowDuplicates || AbstractEvent.getListener(eventClass, this.getClass()) == null) {
            AbstractEvent.addListener(eventClass, this);
        }
    }

    public void unregister(Class<? extends GEvent> eventClass) {
        AbstractEvent.removeListener(eventClass, this);
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void doBeforeEvent(T event) {
        //DO NOTHING
    }

    @Override
    public void doAfterEvent(T event) {
        //DO NOTHING
    }
}