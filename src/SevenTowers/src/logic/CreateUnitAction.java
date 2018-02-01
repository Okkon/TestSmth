package logic;

import tools.PEConst;

public class CreateUnitAction<T extends GameCell> extends AbstractAction {
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
