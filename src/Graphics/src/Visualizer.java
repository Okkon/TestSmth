import logic.AbstractAction;
import logic.PlaceHaving;

public interface Visualizer {
    void setDefaults();

    void showSelectionPossibility(AbstractAction<? extends PlaceHaving> action);
}
