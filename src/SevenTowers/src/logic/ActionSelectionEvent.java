package logic;

public class ActionSelectionEvent extends AbstractEvent {

    private GAction action;

    public ActionSelectionEvent(GAction action) {
        this.action = action;
    }

    @Override
    protected void perform() {
        GameCore.getInstance().setSelectedAction(action);
        action.aimSelectionStep();
    }

    public GAction getAction() {
        return action;
    }
}
