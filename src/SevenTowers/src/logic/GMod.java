package logic;

import java.util.ArrayList;
import java.util.List;

public abstract class GMod<T extends GEvent, H extends GObj> implements GEventListener<T> {
    private List<H> holderList = new ArrayList<>();
    protected Class<? extends GEvent> eventClass;

    public void register(H obj) {
        if (holderList.isEmpty()) {
            AbstractEvent.addListener(eventClass, this);
        }
        holderList.add(obj);
    }

    protected List<H> getHolders() {
        return holderList;
    }

    public void unregister(H obj) {
        holderList.remove(obj);
        if (holderList.isEmpty()) {
            AbstractEvent.removeListener(eventClass, this);
        }
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