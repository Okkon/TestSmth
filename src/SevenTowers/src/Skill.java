import logic.AbstractAction;

public abstract class Skill<T> extends AbstractAction<T> {
    private GUnit actor;
    private boolean endsUnitTurn;

    public GUnit getActor() {
        return actor;
    }

    public void setActor(GUnit actor) {
        this.actor = actor;
    }
}
