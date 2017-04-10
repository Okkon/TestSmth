package com.company.fxapp.filters;

import com.company.fxapp.core.AbstractFilter;

public class ObjTypeFilter<T> extends AbstractFilter<T> {
    private static final ObjTypeFilter INSTANCE = new ObjTypeFilter();

    private ObjTypeFilter() {
    }

    public static ObjTypeFilter getInstance(Class wantedClass) {
        INSTANCE.aClass = wantedClass;
        return INSTANCE;
    }

    private Class aClass;

    @Override
    public boolean isOk(T obj) {
        return aClass.isInstance(obj);
    }
}
