package logic.skills;

import logic.GameCell;

public class Step {
    private final GameCell cell;
    private final int stepPrice;

    public Step(GameCell cell, int stepPrice) {
        this.cell = cell;
        this.stepPrice = stepPrice;
    }

    public GameCell getCell() {
        return cell;
    }

    public int getStepPrice() {
        return stepPrice;
    }

    @Override
    public String toString() {
        return String.format("->%s(%s)", cell, stepPrice);
    }
}
