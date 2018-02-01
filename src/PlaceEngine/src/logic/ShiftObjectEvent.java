package logic;

public class ShiftObjectEvent extends AbstractEvent {
    private GameCell toCell;
    private GameCell fromCell;
    private GObj obj;

    public ShiftObjectEvent(GameCell toCell, GObj obj) {
        this.obj = obj;
        this.toCell = toCell;
        this.fromCell = obj.getPlace();
    }

    @Override
    protected void perform() {
        fromCell.setObj(null);
        obj.setPlace(toCell);
        toCell.setObj(obj);
    }

    public GameCell getToCell() {
        return toCell;
    }

    public GObj getObj() {
        return obj;
    }

    @Override
    public String toString() {
        return String.format("%s is shifted from %s to %s", obj, fromCell, toCell);
    }
}
