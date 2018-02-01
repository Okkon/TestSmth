import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

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
}
