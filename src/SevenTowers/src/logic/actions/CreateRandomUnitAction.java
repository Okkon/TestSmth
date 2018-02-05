package logic.actions;

import logic.*;
import logic.events.CreateObjEvent;

import java.util.List;
import java.util.Random;

public class CreateRandomUnitAction extends AbstractAction {
    private static CreateRandomUnitAction INSTANCE;
    private static Random r = new Random();

    private CreateRandomUnitAction() {
    }

    @Override
    public void init() {
    }

    public static CreateRandomUnitAction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreateRandomUnitAction();
        }
        return INSTANCE;
    }

    @Override
    public void doAction() {
        new CreateObjEvent(findRandomCell(), findUnit()).process();
    }

    private GameCell findRandomCell() {
        List<GameCell> allCells = GBoard.getInstance().getAllCells(VacantCellFilter.getInstance());
        int size = allCells.size();
        return allCells.get(r.nextInt(size));
    }

    private GUnit findUnit() {
        return new GUnit(BaseTypes.getTypes().get(r.nextInt(BaseTypes.values().length)));
    }
}
