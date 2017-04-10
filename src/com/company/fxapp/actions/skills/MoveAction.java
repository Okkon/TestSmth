package com.company.fxapp.actions.skills;

import com.company.fxapp.core.AimType;
import com.company.fxapp.core.GameCell;
import com.company.fxapp.core.Skill;
import com.company.fxapp.events.MoveUnitEvent;
import com.company.fxapp.filters.IsNearFilter;
import com.company.fxapp.filters.VacantCellFilter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MoveAction<T extends GameCell> extends Skill<T> {
    private static MoveAction INSTANCE = new MoveAction();

    private MoveAction() {
    }

    public static MoveAction getInstance() {
        return INSTANCE;
    }

    @Override
    protected void setAimFilters() {
        aimType = AimType.Cell;
        addAimFilter(IsNearFilter.getInstance().setCell(getActor().getPlace()));
        addAimFilter(VacantCellFilter.getInstance());
    }

    @Override
    public void doAction() {
        new MoveUnitEvent(getAim(), getActor()).process();
    }

    public int calculateStepPrice(GameCell fromCell, GameCell toCell) {
        return fromCell.getDistanceToCell(toCell);
    }

    public Map<GameCell, Integer> getReachableCells() {
        final Map<GameCell, Integer> result = new HashMap<>();
        final Queue<GameCell> cellsToCheck = new ArrayDeque<>();
        final int mp = getActor().getMp();
        int mpToCell = 0;
        final GameCell checkingCell = getActor().getPlace();

        while (!cellsToCheck.isEmpty()) {
            final Map<GameCell, Integer> links = checkingCell.getLinks();
            for (Map.Entry<GameCell, Integer> entry : links.entrySet()) {
                final int mpToCheckingCell = entry.getValue() + mpToCell;
                if (mpToCheckingCell <= mp) {
                    final Integer foundWayLength = result.get(entry.getKey());
                    if ((foundWayLength == null) || (mpToCheckingCell < foundWayLength)) {
                        cellsToCheck.add(entry.getKey());
                        result.put(entry.getKey(), mpToCheckingCell);
                    }
                }
            }
        }

        return result;
    }
}
