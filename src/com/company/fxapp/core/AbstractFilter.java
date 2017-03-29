package com.company.fxapp.core;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractFilter<T extends PlaceHaving> implements GFilter<T> {
    private GObj obj;
    private String errorText;
    protected GameCore model = GameCore.getInstance();

    /*@Override
    public GObj getObj() {
        return obj;
    }

    @Override
    public GFilter setObj(GObj obj) {
        this.obj = obj;
        return this;
    }*/

    @Override
    public boolean check(T obj) {
        final boolean ok = isOk(obj);
        if (!ok && errorText != null) {
//            model.error(errorText);
        }
        return ok;
    }

    protected abstract boolean isOk(T obj);

    @Override
    public void filter(Collection<T> collection) {
//        collection.stream().filter(o -> !isOk(o));
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (!isOk(next)) {
                iterator.remove();
            }
        }
    }


}
