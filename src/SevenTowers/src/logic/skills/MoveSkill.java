package logic.skills;

import logic.GUnit;
import logic.GameCell;
import logic.Skill;
import logic.events.StepEvent;
import logic.filters.CanMoveToFilter;
import tools.PEConst;
import utils.XY;

import java.util.*;
import java.util.stream.Collectors;

public class MoveSkill extends Skill {
    private static CanMoveToFilter canMoveToFilter = new CanMoveToFilter();
    private static MoveSkill INSTANCE = new MoveSkill();
    private List<Way> bestWays;

    private MoveSkill() {
    }

    public static MoveSkill getInstance() {
        return INSTANCE;
    }

    @Override
    public void init() {
        setEndsUnitTurn(false);
        addAimFilter("Where to move", PEConst.CELL_FILTER, canMoveToFilter);
    }

    @Override
    protected void setUpFilters() {
        canMoveToFilter.setActor(getActor());
    }

    @Override
    public void doAction() {
        Optional<Way> wayToCell = bestWays
                .stream()
                .filter(way -> way.getEnd().equals(getAim())).findAny();
        if (!wayToCell.isPresent()) {
            throw new RuntimeException("Can't find a way to cell " + getAim());
        }
        for (Step step : wayToCell.get().getSteps()) {
            new StepEvent(getActor(), step.getCell(), step.getStepPrice()).process();
        }
    }

    public int calculateStepPrice(GUnit actor, GameCell fromCell, GameCell toCell) {
        int price = Integer.MAX_VALUE;
        XY start = fromCell.getXy();
        XY end = toCell.getXy();
        if (start.isNear(end)) {
            return XY.getDistance(start, end);
        }
        return price;
    }

    public List<Way> findAllWays(GUnit actor) {
        bestWays = new ArrayList<>();
        GameCell start = actor.getPlace();
        int mp = actor.getMp();
        LinkedList<Way> waysToCheck = new LinkedList<>();
        waysToCheck.add(new Way(start));
        while (!waysToCheck.isEmpty()) {
            Way way = waysToCheck.poll();
            List<Step> possibleSteps = findWhereCanStep(actor, way.getEnd(), mp - way.getLength());
            for (Step step : possibleSteps) {
                int sumLength = step.getStepPrice() + way.getLength();
                Optional<Way> foundWay = bestWays
                        .stream()
                        .filter(w -> w.getEnd().equals(step.getCell()))
                        .findFirst();
                if (!foundWay.isPresent() || foundWay.get().getLength() > sumLength) {
                    Way newWay = way.addStep(step);
                    bestWays.add(newWay);
                    waysToCheck.add(newWay);
                }
            }
        }
        return bestWays;
    }

    private List<Step> findWhereCanStep(GUnit unit, GameCell start, int mp) {
        if (mp < XY.straightLength) {
            return Collections.EMPTY_LIST;
        }
        List<Step> steps = start
                .getLinkedCells()
                .stream()
                .map(gameCell -> new Step(gameCell, calculateStepPrice(unit, start, gameCell)))
                .filter(way -> way.getStepPrice() <= mp)
                .collect(Collectors.toList());
        return steps;
    }
}
