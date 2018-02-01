package logic;

import java.util.List;

public interface GAction<T> {
    void cancel();

    void aimSelectionStep();

    void perform();

    void tryToSelect(Object obj);

    List<ActionAimRequirement> getAims();

}
