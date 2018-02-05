package logic;

import logic.actions.SelectUnitAction;

/**
 * Created by Олег on 05.02.2018.
 */
public class GamePhase extends Phase {
    public GamePhase() {
        setBaseAction(SelectUnitAction.getInstance());
    }
}
