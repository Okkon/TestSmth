package com.company.fxapp.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractAction<T extends PlaceHaving> implements GAction<T> {
    protected AimType aimType = AimType.ObjectsAndCells;
    protected List<T> aims = new ArrayList<>();
    protected List<GFilter> aimFilters = new ArrayList<>();

    protected void addAimFilter(GFilter filter) {
        aimFilters.add(filter);
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

    public void init() {
    }

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

    @Override
    public void onSelect() {
        if (allAimsSelected()) {
            perform();
        } else {
            setAimFilters();
            new FindPossibleAimsEvent(this).process();
        }
    }

    protected boolean allAimsSelected() {
        //action performs if it has selected aims, or if it can't have any aims.
        return AimType.None.equals(aimType) || aims.size() > 1;
    }

    protected abstract void setAimFilters();

    @Override
    public void perform() {
//        model.showSelectionPossibility(null);
        doAction();
        aims.clear();
        afterPerform();
//        model.getPhase().next(this);
    }

    @Override
    public List<T> findPossibleAims() {
        List<GFilter> aimFilters = new ArrayList<>(this.aimFilters);
        List<T> possibleAims = new ArrayList<>();
        final GBoard board = GBoard.getInstance();
        if (AimType.Cell.equals(aimType)) {
            possibleAims.addAll((Collection<? extends T>) board.getAllCells(aimFilters));
        } else if (AimType.Object.equals(aimType)) {
            return (List<T>) board.getUnitList(aimFilters);
        } else if (AimType.ObjectsAndCells.equals(aimType)) {
            possibleAims.add((T) board.getAllCells(aimFilters));
            possibleAims.add((T) board.getUnitList(aimFilters));
        }
        return possibleAims;
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
            if (!filter.check(obj)) {
                return false;
            }
        }
        return true;
    }

    public T getAim() {
        return aims.isEmpty() ? null : aims.get(0);
    }

    public static class FindPossibleAimsEvent extends AbstractEvent {
        private AbstractAction<? extends PlaceHaving> action;
        private List<? extends PlaceHaving> possibleAims;

        public <T extends PlaceHaving> FindPossibleAimsEvent(AbstractAction<T> action) {
            this.action = action;
        }

        public List<? extends PlaceHaving> getPossibleAims() {
            return possibleAims;
        }

        @Override
        protected void perform() {
            possibleAims = action.findPossibleAims();
        }

        public AbstractAction<? extends PlaceHaving> getAction() {
            return action;
        }
    }
}
