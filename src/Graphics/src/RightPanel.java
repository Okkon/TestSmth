import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.AbstractAction;
import logic.ActionAimRequirement;
import logic.GUnit;
import logic.Skill;
import logic.events.ActionSelectionEvent;

import java.util.List;

public class RightPanel extends VBox {
    private UnitInfoPanel unitInfoPanel = new UnitInfoPanel();
    private Label actionNameLabel = new Label();
    private Label actorNameLabel = new Label();
    private final Pane aimsBox = new VBox(5);

    public RightPanel() {
        super(15);
        setPrefWidth(350);
        setId("right-panel");
        getChildren().addAll(
                actionNameLabel,
                actorNameLabel,
                aimsBox,
                unitInfoPanel
        );
    }

    public void showActionSelection(ActionSelectionEvent event) {
        actionNameLabel.setText("Selected Action:" + event.getAction().getClass().getSimpleName());
        if (event.getAction() instanceof Skill) {
            Skill skill = (Skill) event.getAction();
            actorNameLabel.setText("Actor = " + skill.getActor());
            actorNameLabel.setVisible(true);
        } else {
            actorNameLabel.setVisible(false);
        }
        actionNameLabel.setText("Selected Action : " + event.getAction().getClass().getSimpleName());
    }

    public void showUnitInfo(GUnit unit) {
        unitInfoPanel.setUnit(unit);
    }

    public void showAimChoose(AbstractAction.AimChoseEvent event) {
        List<ActionAimRequirement> aims = event.getAction().getAims();
        ObservableList<Node> children = aimsBox.getChildren();
        children.clear();
        for (ActionAimRequirement aim : aims) {
            children.add(new Label(aim.toString()));
        }
    }
}
