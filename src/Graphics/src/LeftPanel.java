import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logic.GAction;
import logic.GameCore;
import logic.events.ActionSelectionEvent;

/**
 * Created by Олег on 05.02.2018.
 */
public class LeftPanel extends VBox {

    private final TextField coordsTextField = new TextField("test text");
    ;
    private final TextField sceneCoordsTextField = new TextField("test text");
    ;
    private final TextField screenCoordsTextField = new TextField("test text");
    ;

    public LeftPanel() {
        super();
        setId("left-panel");

        final ObservableList<Node> children = getChildren();
        children.addAll(
                new Label("COORDS:"), coordsTextField,
                new Label("Scene COORDS:"), sceneCoordsTextField,
                new Label("Screen COORDS:"), screenCoordsTextField
        );

        for (GAction action : GameCore.getInstance().getActionList()) {
            final Button button = new Button(action.getClass().getSimpleName());
            button.setOnMouseClicked(event -> new ActionSelectionEvent(action).process());
            button.setId("okb");
            children.add(button);
        }
    }

    public void showMousePositionInfo(MouseEvent event) {
        coordsTextField.setText(
                String.format("Coords: %s-%s", event.getX(), event.getY())
        );
        sceneCoordsTextField.setText(String.format(
                "Scene coords: %s-%s", event.getSceneX(), event.getSceneY())
        );
        screenCoordsTextField.setText(String.format(
                "Screen Coords: %s-%s", event.getScreenX(), event.getScreenY())
        );
    }
}
