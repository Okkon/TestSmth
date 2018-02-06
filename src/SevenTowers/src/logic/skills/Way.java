package logic.skills;

import logic.GameCell;

import java.util.ArrayList;
import java.util.List;

public class Way {
    private final GameCell start;
    private GameCell end;
    private int length;
    private List<Step> stepList = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("%s-%s(%s)", start, end, length);
    }

    public Way(GameCell start) {
        this.start = start;
        this.end = start;
        this.length = 0;
    }

    public GameCell getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }

    public Way addStep(Step step) {
        Way way = new Way(start);
        way.end = step.getCell();
        way.length = this.length + step.getStepPrice();
        way.stepList.addAll(this.stepList);
        way.stepList.add(step);
        return way;
    }

    public List<Step> getSteps() {
        return stepList;
    }
}
