package logic;

import java.util.List;

public interface GAction {
    ActionAimRequirement cancel();

    void aimSelectionStep();

    void perform();

    void tryToSelect(Object obj);

    List<ActionAimRequirement> getAims();

}
