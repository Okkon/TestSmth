package logic;

import static tools.PEConst.*;

public class ShiftObjAction extends AbstractAction {
    private static ShiftObjAction INSTANCE = new ShiftObjAction();

    private ShiftObjAction() {
    }

    @Override
    public void init() {
        addAimFilter("Object to move", OBJ_FILTER);
        addAimFilter("Place where to move", CELL_FILTER, VACANT_CELL_FILTER);
    }

    public static ShiftObjAction getInstance() {
        return INSTANCE;
    }

    @Override
    public void doAction() {
        final GObj obj = (GObj) getAim(0);
        final GameCell place = (GameCell) getAim();
        new ShiftObjectEvent(place, obj).process();
    }


}
