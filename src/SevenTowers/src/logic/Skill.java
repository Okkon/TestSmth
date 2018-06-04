package logic;

public abstract class Skill extends AbstractAction {
    private GUnit actor;
    private boolean endsUnitTurn = true;

    public GUnit getActor() {
        return actor;
    }

    public void setActor(GUnit actor) {
        this.actor = actor;
    }

    public boolean endsTurn() {
        return endsUnitTurn;
    }

    public void setEndsUnitTurn(boolean endsUnitTurn) {
        this.endsUnitTurn = endsUnitTurn;
    }
}
