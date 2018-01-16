import logic.AbstractAction;
import logic.GAction;

public class SelectUnitAction<T extends GUnit> extends AbstractAction<T> {
    private static SelectUnitAction INSTANCE = new SelectUnitAction();

    private SelectUnitAction() {
    }

    public static SelectUnitAction getInstance() {
        return INSTANCE;
    }

    @Override
    public void init() {
        addAimFilter("Unit to select", STConst.UNIT_FILTER);
    }

    @Override
    public void doAction() {
        GUnit selectedObj = getAim();
        GAction baseAction = selectedObj.getBaseAction();
        if (baseAction instanceof Skill) {
            Skill skill = (Skill) baseAction;
            skill.setActor(selectedObj);
        }
        new ActionSelectionEvent(baseAction).process();
    }


}
