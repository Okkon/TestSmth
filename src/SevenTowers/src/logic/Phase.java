package logic;


import logic.actions.SelectUnitAction;

public abstract class Phase {
    private GAction baseAction;

    public GAction getBaseAction() {
        return baseAction;
    }

    public void setBaseAction(SelectUnitAction baseAction) {
        this.baseAction = baseAction;
    }
}
