
public class ShiftObjectEvent extends AbstractEvent {
    private GameCell toCell;
    private GameCell fromCell;
    private GObj unit;

    public ShiftObjectEvent(GameCell toCell, GObj unit) {
        this.unit = unit;
        this.toCell = toCell;
        this.fromCell = unit.getPlace();
    }

    @Override
    protected void perform() {
        fromCell.setObj(null);
        unit.setPlace(toCell);
        toCell.setObj(unit);
    }

    public GameCell getToCell() {
        return toCell;
    }

    public GObj getObj() {
        return unit;
    }
}
