package logic;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAction implements GAction {
    private List<ActionAimRequirement> actionAimRequirements = new ArrayList<>();

    protected AbstractAction() {
        init();
    }

    protected void addAimFilter(String aimName, GFilter... filters) {
        actionAimRequirements.add(new ActionAimRequirement(aimName, filters));
    }

    public abstract void init();

    @Override
    public void cancel() {
        ActionAimRequirement lastSelectedAim = getLastSelectedAim();
        if (lastSelectedAim != null) {
            lastSelectedAim.setSelectedAim(null);
            aimSelectionStep();
        }
    }

    private ActionAimRequirement getAimRequirement() {
        for (ActionAimRequirement actionAimRequirement : actionAimRequirements) {
            if (actionAimRequirement.getSelectedAim() == null) {
                return actionAimRequirement;
            }
        }
        return null;
    }

    private ActionAimRequirement getLastSelectedAim() {
        for (int i = actionAimRequirements.size() - 1; i >= 0; i--) {
            if (actionAimRequirements.get(i).getSelectedAim() != null) {
                return actionAimRequirements.get(i);
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
            new AimChoseEvent(this).process();
        }
    }

    protected void setUpFilters() {
        //do nothing - override in successors
    }

    protected boolean allAimsSelected() {
        return getAimRequirement() == null;
    }

    @Override
    public void perform() {
        new ActionPerformEvent(this).process();
        clearAims();
    }

    @Override
    public final void tryToSelect(Object obj) {
        if (canSelect(obj)) {
            getAimRequirement().setSelectedAim(obj);
            aimSelectionStep();
        }
    }

    @Override
    public List<ActionAimRequirement> getAims() {
        return actionAimRequirements;
    }


    private void clearAims() {
        for (ActionAimRequirement actionAimRequirement : actionAimRequirements) {
            actionAimRequirement.setSelectedAim(null);
        }
    }

    public abstract void doAction();

    public boolean canSelect(Object obj) {
        List<? extends GFilter> filters = getAimRequirement().getFilters();
        for (GFilter filter : filters) {
            if (!filter.isOk(obj)) {
                new FailedSelectionAttemptEvent(this, filter, obj).process();
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public <T> T getAim() {
        ActionAimRequirement lastSelectedAim = getLastSelectedAim();
        if (lastSelectedAim != null) {
            return lastSelectedAim.getSelectedAim();
        }
        return null;
    }

    public <T> T getAim(int index) {
        return actionAimRequirements.get(index).getSelectedAim();
    }

    public String getDescription() {
        return "Description of " + getClass().getSimpleName();
    }


    public static class AimChoseEvent extends AbstractEvent {
        private AbstractAction action;
        private ActionAimRequirement aimRequirement;

        public ActionAimRequirement getAimRequirement() {
            return aimRequirement;
        }

        public AimChoseEvent(AbstractAction action) {
            this.action = action;
            this.aimRequirement = action.getAimRequirement();
        }

        @Override
        protected void perform() {
            //do nothing, needed for graphics
        }

        public AbstractAction getAction() {
            return action;
        }

        @Override
        public String toString() {
            return String.format("Aim to choose: %s", aimRequirement);
        }
    }

    public class ActionPerformEvent extends AbstractEvent {
        private final AbstractAction action;

        public ActionPerformEvent(AbstractAction action) {
            this.action = action;
        }

        @Override
        protected void perform() {
            action.doAction();
        }

        @Override
        public String toString() {
            return action + " is performed";
        }

        public AbstractAction getAction() {
            return action;
        }
    }
}
