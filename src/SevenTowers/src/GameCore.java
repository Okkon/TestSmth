import logic.GAction;
import logic.PlaceHaving;
import logic.ShiftObjAction;

import java.util.Arrays;
import java.util.List;


public class GameCore {
    //----------Singleton-----------
    private static GameCore INSTANCE = new GameCore();

    private GameCore() {
    }

    public static GameCore getInstance() {
        return INSTANCE;
    }

    //--------Fields------------------
    private List<GAction> actionList = Arrays.asList(CreateUnitAction.getInstance(), SelectUnitAction.getInstance(), ShiftObjAction.getInstance());
    private GAction selectedAction = actionList.get(0);

    //-----------Logic-----------------------------------------
    public void press(PlaceHaving cell) {
        selectedAction.tryToSelect(cell);
    }

    //-------------Getters & Setters----------------------

    public List<GAction> getActionList() {
        return actionList;
    }

    public GAction getSelectedAction() {
        return selectedAction;
    }

    public void setSelectedAction(GAction selectedAction) {
        this.selectedAction = selectedAction;
    }

}
