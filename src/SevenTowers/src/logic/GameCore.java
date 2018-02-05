package logic;

import logic.actions.CreateRandomUnitAction;
import logic.actions.CreateUnitAction;
import logic.actions.SelectUnitAction;
import tools.PEConst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    private List<GAction> actionList = Arrays.asList(
            CreateUnitAction.getInstance(),
            SelectUnitAction.getInstance(),
            CreateRandomUnitAction.getInstance(),
            ShiftObjAction.getInstance()
    );
    private GAction selectedAction = actionList.get(0);
    private Phase phase = new GamePhase();

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

    public List findAims(ActionAimRequirement actionAimRequirements) {
        List<GFilter> filters = actionAimRequirements.getFilters();
        ArrayList<Object> possibleAims = new ArrayList<>();
        GFilter mainFilter = filters.get(0);
        if (PEConst.OBJ_FILTER == mainFilter || STConst.UNIT_FILTER == mainFilter) {
            possibleAims.addAll(getAllObjects());
        } else if (PEConst.CELL_FILTER == mainFilter) {
            possibleAims.addAll(getAllCells());
        }
        for (int i = 1; i < filters.size(); i++) {
            GFilter filter = filters.get(i);
            filter.filter(possibleAims);
        }

        return possibleAims;
    }

    private Collection<GameCell> getAllCells() {
        return GBoard.getInstance().getAllCells();
    }

    private Collection<GObj> getAllObjects() {
        return GBoard.getInstance().getObjList();
    }


}
