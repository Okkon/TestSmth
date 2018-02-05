package logic.actions;

import logic.AbstractAction;
import logic.ClassFilter;
import logic.GUnit;
import logic.UnitType;
import logic.events.CreateObjEvent;
import tools.PEConst;

public class CreateUnitAction extends AbstractAction {
    private static CreateUnitAction INSTANCE;

    private CreateUnitAction() {
    }

    @Override
    public void init() {
        addAimFilter("Unit type to create", ClassFilter.getInstance(UnitType.class));
        addAimFilter("Cell where to create unit", PEConst.CELL_FILTER, PEConst.VACANT_CELL_FILTER);
    }

    public static CreateUnitAction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreateUnitAction();
        }
        return INSTANCE;
    }

    @Override
    public void doAction() {
        new CreateObjEvent((getAim()), new GUnit(getAims().get(0).getSelectedAim())).process();
    }
}
