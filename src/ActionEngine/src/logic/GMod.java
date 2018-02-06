package logic;

public abstract class GMod<T extends GEvent> implements GEventListener<T> {
    protected final int value;

    public GMod(int value, Class<? extends GEvent> eventClass) {
        this.value = value;
        register(eventClass);
    }

    public void register(Class<? extends GEvent> eventClass) {
        if (AbstractEvent.getListener(eventClass, this.getClass()) == null) {
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