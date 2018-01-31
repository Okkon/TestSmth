import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.AbstractAction;
import logic.UnitType;

import java.util.List;


public class UnitTypeSelector {
    private final ObjectInfoPanel infoPanel;
    private UnitType selectedUnitType;
    private Stage dialog;
    private ImageView lastSelected;

    public UnitTypeSelector(final List<UnitType> types, final Stage dialog, AbstractAction action) {
        this.dialog = dialog;
        dialog.setX(320);
        dialog.setY(0);

        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setPadding(new Insets(5, 10, 5, 10));
        pane.setStyle("-fx-background-color: ivory;");
        final int unitPaneWidth = 3;
        infoPanel = new ObjectInfoPanel();
        PlayerInfoPanel playerPanel = new PlayerInfoPanel();
        playerPanel.setPlayer(new Player());
        for (int i = 0; i < types.size(); i++) {
            final UnitType unitType = types.get(i);
            final Image image = ImageHelper.getImage(unitType);
            final ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            imageView.setPreserveRatio(true);
            imageView.setOnMousePressed(mouseEvent -> {
                selectedUnitType = unitType;
                infoPanel.setObj(unitType);
                if (lastSelected != null) {
                    lastSelected.setStyle("-fx-opacity: 1;");
                }
                lastSelected = imageView;
                imageView.setStyle("-fx-opacity: 0.5;");
                action.tryToSelect(unitType);
            });
            HBox box = new HBox();
            box.getChildren().add(imageView);
            box.getStyleClass().add("unitSelector");

            pane.add(box, i % unitPaneWidth, i / unitPaneWidth);
        }
        infoPanel.setObj(types.get(0));
        pane.add(playerPanel, 0, types.size() / unitPaneWidth + 1, unitPaneWidth, GridPane.REMAINING);
        pane.add(infoPanel, unitPaneWidth, 0, GridPane.REMAINING, GridPane.REMAINING);
        final Label label = new Label();
        pane.add(label, 0, types.size() / unitPaneWidth + 1, unitPaneWidth, GridPane.REMAINING);

        final Scene scene = new Scene(
                HBoxBuilder.create().styleClass("modal-dialog").children(
                        pane
                ).build(),
                Color.GRAY
        );

        dialog.setOnCloseRequest(windowEvent -> {
            UnitTypeSelector.this.close();
//            GameModel.MODEL.cancel();
        });
//        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        dialog.setScene(scene);
        dialog.show();
    }

    public void close() {
        dialog.close();
    }

    public void setUnitCounter(int unitCounter) {
        dialog.setTitle("Units left: " + unitCounter);
    }
}
