package logic;

public interface GEventListener<T extends GEvent> {
    void doBeforeEvent(T event);

    void doAfterEvent(T event);
}
