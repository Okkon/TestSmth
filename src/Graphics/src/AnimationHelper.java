import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.HashSet;

public class AnimationHelper {
    private static final HashSet<Visualizer> visualizers = new HashSet<>();

    public static Timeline createStrokeAnimation(Shape shape, Color color) {
        return createFrameAnimation(
                shape.strokeProperty(),
                color
        );
    }

    public static Timeline createFillAnimation(Shape shape, Color color) {
        return createFrameAnimation(
                shape.fillProperty(),
                color
        );
    }

    private static Timeline createFrameAnimation(ObjectProperty<Paint> property, Color color) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(property, color);
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

    public static ScaleTransition createScaleAnimation(Node node, int from, int to) {
        ScaleTransition transition = new ScaleTransition();
        transition.setNode(node);
        transition.setDuration(GraphicConstants.ANIMATION_DURATION);
        transition.setFromX(from);
        transition.setFromY(from);
        transition.setToX(to);
        transition.setToY(to);
        return transition;
    }
}
