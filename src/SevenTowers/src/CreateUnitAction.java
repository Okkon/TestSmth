public class CreateUnitAction<T extends GameCell> extends AbstractAction<T> {
    private static CreateUnitAction INSTANCE;

    private CreateUnitAction() {
    }

    @Override
    public void init() {
        addAimFilter("Cell where to create unit", AimType.Cell, VacantCellFilter.getInstance());
    }

    public static CreateUnitAction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreateUnitAction();
        }
        return INSTANCE;
    }

    private final UnitType footmanUnitType = new UnitType(25, 6);

    @Override
    public void doAction() {
        new CreateUnitEvent((getAim()), new GUnit(footmanUnitType)).process();
    }
}
