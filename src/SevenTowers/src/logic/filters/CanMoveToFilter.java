package logic.filters;

import logic.AbstractFilter;
import logic.GUnit;
import logic.GameCell;
import logic.skills.MoveSkill;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CanMoveToFilter<T extends GameCell> extends AbstractFilter<T> {
    private GUnit actor;

    @Override
    public boolean isOk(T cell) {
        return MoveSkill.getInstance()
                .findAllWays(actor)
                .stream()
                .anyMatch(way -> way.getEnd().equals(cell));
    }

    @Override
    public List<T> filter(List<T> collection) {
        Set<GameCell> cellSet = MoveSkill.getInstance().findAllWays(actor)
                .stream()
                .map(way -> way.getEnd())
                .collect(Collectors.toSet());
        return collection
                .stream()
                .filter(e -> cellSet.contains(e))
                .collect(Collectors.toList());
    }

    public void setActor(GUnit actor) {
        this.actor = actor;
    }
}
