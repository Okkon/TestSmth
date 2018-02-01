package logic;

public class CreateObjEvent extends AbstractEvent {
    private GameCell place;
    private GObj obj;

    public CreateObjEvent(GameCell place, GObj obj) {
        this.place = place;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return String.format("%s created in %s", obj, place);
    }

    @Override
    protected void perform() {
        obj.setPlace(place);
        place.setObj(obj);
        GBoard.getInstance().addUnit(obj);
    }

    public GameCell getPlace() {
        return place;
    }

    public GObj getObj() {
        return obj;
    }
}
