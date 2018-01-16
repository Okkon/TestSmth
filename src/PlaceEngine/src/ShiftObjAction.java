
public class ShiftObjAction<T> extends AbstractAction<T> {
    private static ShiftObjAction INSTANCE = new ShiftObjAction();

    private ShiftObjAction() {
    }

    @Override
    public void init() {
        addAimFilter("Object to move", AimType.Object, ClassFilter.getInstance(GObj.class));
        addAimFilter("Place where to move", AimType.Cell, ClassFilter.getInstance(GameCell.class));
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
