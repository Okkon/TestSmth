package com.company.fxapp.graphics;

import com.company.fxapp.core.AbstractAction;
import com.company.fxapp.core.GObj;
import com.company.fxapp.core.GameCore;
import com.company.fxapp.core.PlaceHaving;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class UnitVisualizer extends Circle implements Visualizer {

    public UnitVisualizer(double x, double y, int r, GObj obj) {
        super(x, y, r);
        setFill(Color.GOLD);
        setOnMouseClicked(o -> GameCore.getInstance().press(obj));
    }

    @Override
    public void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action) {
        final Timeline timeline = createColorAnimation(Color.RED);
        if (action != null) {
            timeline.play();
            //place animation
        } else {
            timeline.stop();
            //stop animation
        }
    }

    private Timeline createColorAnimation(Color color) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(this.fillProperty(), color);
        final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv);
        timeline.getKeyFrames().add(kf);
        return timeline;
    }
}
