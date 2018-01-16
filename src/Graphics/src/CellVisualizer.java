import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellVisualizer extends Rectangle implements Visualizer {
    private final Color baseColor = Color.DARKGRAY;
    private final Color baseStrokeColor = Color.GRAY;
    private Timeline timeline;

    public CellVisualizer(int x, int y, int width, int height, GameCell cell) {
        super(x, y, width, height);
        setDefaults();
        setOnMouseClicked(
                event -> GameCore.getInstance().press(cell)
        );
    }

    @Override
    public void setDefaults() {
        if (timeline != null) {
            timeline.stop();
        }
        setFill(baseColor);
        setStroke(baseStrokeColor);
    }

    @Override
    public void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action) {
        setDefaults();
        if (action != null) {
            timeline = AnimationHelper.createColorAnimation(this, baseColor.brighter());
            timeline.play();
        }
    }
}
