package logic;

public class GObj implements PlaceHaving, OwnerHaving {
    private GameCell place;
    private Player player;

    @Override
    public Player getOwner() {
        return player;
    }

    public void setOwner(Player player) {
        this.player = player;
    }

    @Override
    public GameCell getPlace() {
        return place;
    }

    public void setPlace(GameCell place) {
        this.place = place;
    }


}
