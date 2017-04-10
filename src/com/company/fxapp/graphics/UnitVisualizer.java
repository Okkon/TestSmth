package com.company.fxapp.graphics;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.GObj;
import com.company.fxapp.core.GameCore;
import com.company.fxapp.core.PlaceHaving;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UnitVisualizer extends Circle implements Visualizer {
    private final Color baseColor = Color.GOLD;
    private Timeline timeline;

    public UnitVisualizer(double x, double y, int r, GObj obj) {
        super(x, y, r);
        setDefaultColors();
        setOnMouseClicked(o -> GameCore.getInstance().press(obj));
    }

    private void setDefaultColors() {
        setFill(baseColor);
    }

    @Override
    public void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action) {
        if (timeline != null) {
            timeline.stop();
            setDefaultColors();
        }
        if (action != null) {
            timeline = AnimationHelper.createColorAnimation(this, Color.RED);
            timeline.play();
        }
    }
}
