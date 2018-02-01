import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import utils.XY_D;

import java.util.ArrayList;
import java.util.List;


public class GraphicsHelper {
    private static GraphicsHelper INSTANCE;
    private List<Transition> transitions;

    private GraphicsHelper() {
        transitions = new ArrayList<>();
    }

    public static GraphicsHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphicsHelper();
        }
        return INSTANCE;
    }

    public GraphicsHelper addTransition(Transition transition) {
        transitions.add(transition);
        return INSTANCE;
    }

    public void play() {
        SequentialTransition transition = new SequentialTransition();
        for (Transition subTransition : transitions) {
            transition.getChildren().add(subTransition);
        }
        transition.play();
        transitions.clear();
    }


    private XY_D getRectangleCenter(Rectangle rectangle) {
        final double minX = rectangle.getBoundsInParent().getMinX();
        final double minY = rectangle.getBoundsInParent().getMinY();
        final double maxX = rectangle.getBoundsInParent().getMaxX();
        final double maxY = rectangle.getBoundsInParent().getMaxY();
        return new XY_D((minX + maxX) / 2, (minY + maxY) / 2);
    }
}
