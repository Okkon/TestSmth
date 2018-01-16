import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UnitVisualizer extends Circle implements Visualizer {
    private final Color baseColor = Color.GOLD;
    private Timeline timeline;

    public UnitVisualizer(double x, double y, int r, GObj obj) {
        super(x, y, r);
        setDefaults();
        setOnMouseClicked(o -> GameCore.getInstance().press(obj));
    }


    @Override
    public void setDefaults() {
        if (timeline != null) {
            timeline.stop();
        }
        setFill(baseColor);
    }

    @Override
    public void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action) {
        setDefaults();
        if (action != null) {
            timeline = AnimationHelper.createColorAnimation(this, Color.RED);
            timeline.play();
        }
    }
}
