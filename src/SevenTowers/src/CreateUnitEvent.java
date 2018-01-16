import logic.AbstractEvent;
import logic.GBoard;
import logic.GObj;
import logic.GameCell;

public class CreateUnitEvent extends AbstractEvent {
    private GameCell place;
    private GObj unit;

    public CreateUnitEvent(GameCell place, GObj unit) {
        this.place = place;
        this.unit = unit;
    }

    @Override
    protected void perform() {
        unit.setPlace(place);
        place.setObj(unit);
        GBoard.getInstance().addUnit(unit);
    }

    public GameCell getPlace() {
        return place;
    }

    public GObj getObj() {
        return unit;
    }
}
