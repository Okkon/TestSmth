package com.company.fxapp.graphics;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.GameCell;
import com.company.fxapp.core.GameCore;
import com.company.fxapp.core.PlaceHaving;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellVisualizer extends Rectangle implements Visualizer {
    private final Color baseColor = Color.DARKGRAY;
    private final Color baseStrokeColor = Color.GRAY;
    private Timeline timeline;

    public CellVisualizer(int x, int y, int width, int height, GameCell cell) {
        super(x, y, width, height);
        setDefaultColors();
        setOnMouseClicked(
                event -> GameCore.getInstance().press(cell)
        );
    }

    private void setDefaultColors() {
        setFill(baseColor);
        setStroke(baseStrokeColor);
    }

    @Override
    public void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action) {
        if (timeline != null) {
            timeline.stop();
            setDefaultColors();
        }
        if (action != null) {
            timeline = AnimationHelper.createColorAnimation(this, baseColor.brighter());
            timeline.play();
        }
    }
}
