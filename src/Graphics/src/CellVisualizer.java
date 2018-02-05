import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.AbstractAction;
import logic.GameCell;
import logic.GameCore;

import static javafx.scene.paint.Color.CHARTREUSE;

public class CellVisualizer extends Rectangle implements Visualizer {
    private final Color baseColor = GraphicConstants.CELL_COLOR;
    private final Color baseStrokeColor = GraphicConstants.CELL_COLOR_STROKE;
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
    public void showSelectionPossibility(AbstractAction action) {
        setDefaults();
        setFill(CHARTREUSE);
        /*if (action != null) {
            timeline = AnimationHelper.createFillAnimation(this, baseColor.brighter());
            timeline.play();
        }*/
    }
}
