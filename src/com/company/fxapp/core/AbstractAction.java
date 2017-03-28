package com.company.fxapp.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAction<T extends PlaceHaving> implements GAction<T> {
    protected AimType aimType = AimType.ObjectsAndCells;
    protected List<T> aims = new ArrayList<>();
    protected List<GFilter> aimFilters = new ArrayList<>();
    protected List<GFilter> preferableAimFilters = new ArrayList<>();
    protected List<GFilter> ownerFilters = new ArrayList<>();

    protected void addAimFilter(GFilter filter) {
        aimFilters.add(filter);
    }

    protected void addPreferableAimFilter(GFilter filter) {
        preferableAimFilters.add(filter);
    }

    protected void removeAimFilter(Class filterClass) {
        aimFilters.remove(findAimFilterByClass(filterClass));
    }

    protected GFilter findAimFilterByClass(Class filterClass) {
        for (GFilter filter : aimFilters) {
            if (filterClass.isInstance(filter)) {
                return filter;
            }
        }
        return null;
    }

    public AbstractAction() {
        init();
    }

    public abstract void init();

    @Override
    public void cancel() {
        if (aims.size() > 0) {
            aims.remove(aims.size() - 1);
            onSelect();
        } else {
//            model.select(null);
//            model.setAction(model.getPhaseAction());
        }
    }

    public List<GFilter> getAimFilters() {
        return aimFilters;
    }

    public void onSelect() {
        if (!allAimsSelected()) {
            setAimFilters();
//            model.showSelectionPossibility(getPossibleAims());
        } else {
            perform();
        }
    }

    protected boolean allAimsSelected() {
        //action performs if it has selected aims, or if it can't have any aims.
        return aimFilters.size() == 0 || aims.size() > 0;
    }

    protected void setAimFilters() {
        //place to add filters
    }

    @Override
    public void perform() {
//        model.showSelectionPossibility(null);
        doAction();
        aims.clear();
        afterPerform();
//        model.getPhase().next(this);
    }

    @Override
    public List<T> getPossibleAims() {
        List<GFilter> aimFilters = new ArrayList<>(this.aimFilters);
        aimFilters.addAll(preferableAimFilters);
        /*if (actor != null) {
            for (GFilter aimFilter : aimFilters) {
                aimFilter.setObj(actor);
            }
        }*/
        final GBoard board = GBoard.getInstance();
        if (AimType.Cell.equals(aimType)) {
            return (List<T>) board.getAllCells();
        }
        if (AimType.Object.equals(aimType)) {
            return (List<T>) board.getUnitList();
        }
        if (AimType.ObjectsAndCells.equals(aimType)) {
            List<T> possibleAims = Collections.EMPTY_LIST;
            possibleAims.add((T) board.getAllCells());
            possibleAims.add((T) board.getUnitList());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public final void tryToSelect(T obj) {
        if (canSelect(obj)) {
            aims.add(obj);
            onSelect();
        }
    }

    public List<T> getAims() {
        return aims;
    }

    protected void afterPerform() {
    }

    public abstract void doAction();

    public boolean canSelect(T obj) {
        for (GFilter filter : aimFilters) {
//            filter.setObj(getActor());
            if (!filter.check(obj)) {
                return false;
            }
        }
        return true;
    }

    public boolean canBeSelected() {
        /*for (GFilter filter : ownerFilters) {
            if (!filter.check(getActor())) {
                return false;
            }
        }*/
        return true;
    }

    public T getAim() {
        return aims.isEmpty() ? null : aims.get(0);
    }
}
