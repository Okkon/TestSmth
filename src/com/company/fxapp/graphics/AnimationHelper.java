package com.company.fxapp.graphics;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.HashSet;

public class AnimationHelper {

    private static final HashSet<Visualizer> visualizers = new HashSet<>();

    public static Timeline createColorAnimation(Shape shape, Color color) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(shape.fillProperty(), color);
        final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv);
        timeline.getKeyFrames().add(kf);
        return timeline;
    }

    public static void addAnimatedVisualizer(Visualizer visualizer) {
        visualizers.add(visualizer);
    }

    public static void clearAnimations() {
        visualizers.forEach(Visualizer::setDefaults);
        visualizers.clear();
    }
}
