package logic;

public abstract class Skill extends AbstractAction {
    private GUnit actor;
    private boolean endsUnitTurn;

    public GUnit getActor() {
        return actor;
    }

    public void setActor(GUnit actor) {
        this.actor = actor;
    }
}
