import java.util.ArrayList;
import java.util.List;

// T - aim class
public abstract class AbstractAction<T> implements GAction<T> {
    private List<ActionAim<T>> actionAims = new ArrayList<>();

    protected AbstractAction() {
        init();
    }

    protected void addAimFilter(String aimName, AimType aimType, GFilter<T>... filters) {
        actionAims.add(new ActionAim<T>(aimName, aimType, filters));
    }

    public abstract void init();

    @Override
    public void cancel() {
        ActionAim<T> lastSelectedAim = getLastSelectedAim();
        if (lastSelectedAim != null) {
            lastSelectedAim.setSelectedAim(null);
            aimSelectionStep();
        }
    }

    private ActionAim getAimToSelect() {
        for (ActionAim actionAim : actionAims) {
            if (actionAim.getSelectedAim() == null) {
                return actionAim;
            }
        }
        return null;
    }

    private ActionAim<T> getLastSelectedAim() {
        for (int i = actionAims.size() - 1; i >= 0; i--) {
            if (actionAims.get(i).getSelectedAim() != null) {
                return actionAims.get(i);
            }
        }
        return null;
    }

    @Override
    public void aimSelectionStep() {
        if (allAimsSelected()) {
            perform();
        } else {
            setUpFilters();
            new AimSelectionEvent(this).process();
        }
    }

    protected void setUpFilters() {
        //do nothing
    }

    protected boolean allAimsSelected() {
        return getAimToSelect() == null;
    }

    @Override
    public void perform() {
        doAction();
        afterPerform();
    }

    @Override
    public final void tryToSelect(Object obj) {
        if (canSelect(obj)) {
            getAimToSelect().setSelectedAim(obj);
            aimSelectionStep();
        }
    }

    protected void afterPerform() {
        clearAims();
    }

    private void clearAims() {
        for (ActionAim<T> actionAim : actionAims) {
            actionAim.setSelectedAim(null);
        }
    }

    public abstract void doAction();

    public boolean canSelect(Object obj) {
        List<? extends GFilter> filters = getAimToSelect().getFilters();
        for (GFilter filter : filters) {
            if (!filter.check(obj)) {
                return false;
            }
        }
        return true;
    }

    public T getAim() {
        if (getLastSelectedAim() != null) {
            return getLastSelectedAim().getSelectedAim();
        }
        return null;
    }

    public T getAim(int index) {
        return actionAims.get(index).getSelectedAim();
    }


    public static class AimSelectionEvent extends AbstractEvent {
        private AbstractAction action;
        private List<?> possibleAims;

        public AimSelectionEvent(AbstractAction action) {
            this.action = action;
        }

        public List<?> getPossibleAims() {
            return possibleAims;
        }

        @Override
        protected void perform() {
            possibleAims = action.getAimToSelect().findPossibleAims();
        }

        public AbstractAction getAction() {
            return action;
        }
    }
}
