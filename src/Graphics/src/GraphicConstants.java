import javafx.scene.paint.Color;

public class GraphicConstants {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;

    public static final int CELL_SIZE = 65;
    public static final int CELL_TAP = 5;
    public static final Color CELL_COLOR = Color.LIGHTGREY;
    public static final Color CELL_COLOR_STROKE = Color.BISQUE;

    public static final Color VISUALIZER_OUTER_BORDER_COLOR = Color.AQUA;
    public static final int VISUALIZER_OUTER_BORDER_SIZE = 3;

    public static final int VISUALIZER_SIZE = (CELL_SIZE - VISUALIZER_OUTER_BORDER_SIZE * 2) / 2;
    public static final int VISUALIZER_BORDER_SIZE = 3;

    public static final double DIALOG_TYPE_SELECTOR_X = 920;
    public static final double DIALOG_TYPE_SELECTOR_Y = 320;
    public static final int DIALOG_TYPE_SELECTOR_IMAGE_VIEW_SIDE = 64;
}
