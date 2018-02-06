package logic;

public interface GEventListener<T extends GEvent> {
    int BASE_PRIORITY = 100;

    default void doBeforeEvent(T event) {
        //DO NOTHING
    }

    default void doAfterEvent(T event) {
        //DO NOTHING
    }

    default double getPriority() {
        return BASE_PRIORITY;
    }

    default boolean isToBeRemoved() {
        return false;
    }
}
