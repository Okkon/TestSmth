package logic;

import static tools.PEConst.OBJ_FILTER;
import static tools.PEConst.VACANT_CELL_FILTER;

public class ShiftObjAction<T> extends AbstractAction<T> {
    private static ShiftObjAction INSTANCE = new ShiftObjAction();

    private ShiftObjAction() {
    }

    @Override
    public void init() {
        addAimFilter("Object to move", OBJ_FILTER);
        addAimFilter("Place where to move", VACANT_CELL_FILTER);
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
