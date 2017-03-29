package com.company.fxapp;

import com.company.fxapp.core.*;
import com.company.fxapp.events.ActionSelectionEvent;
import com.company.fxapp.events.CreateUnitEvent;
import com.company.fxapp.events.ShiftUnitEvent;
import com.company.fxapp.graphics.BottomPane;
import com.company.fxapp.graphics.CellVisualizer;
import com.company.fxapp.graphics.UnitVisualizer;
import com.company.fxapp.utils.XY;
import com.company.fxapp.utils.XY_D;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyApp extends Application implements GEventListener<GEvent> {

    //---------GRAPHIC------------
    private final int windowWidth = 600;
    private final int windowHeight = 600;
    private final Pane mainPane = new Pane();
    private final TextField actionNameField = new TextField("test text");

    //----------LOGIC--------
    private final GameCore gameCore = GameCore.getInstance();
    private final HashMap<GameCell, Rectangle> cellToRectMap = new HashMap<>();
    private final HashMap<GObj, UnitVisualizer> objToVisualizerMap = new HashMap<>();


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simple Application");
        primaryStage.show();


        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-background-color: #336699;");
        TextField coordsTextField = new TextField("test text");
        TextField sceneCoordsTextField = new TextField("test text");
        TextField screenCoordsTextField = new TextField("test text");

        final ObservableList<Node> children = vBox.getChildren();
        children.addAll(
                new Label("COORDS:"), coordsTextField,
                new Label("Scene COORDS:"), sceneCoordsTextField,
                new Label("Screen COORDS:"), screenCoordsTextField,
                new Label("Selected action:"), actionNameField
        );
        for (GAction action : gameCore.getActionList()) {
            final Button button = new Button(action.getClass().getSimpleName());
            button.setOnMouseClicked(event -> new ActionSelectionEvent(action).process());
            children.add(button);
        }

        mainPane.setStyle("-fx-border-color: orange; -fx-border-width: 2; -fx-background-color: #333366;");
        BorderPane root = new BorderPane();
        mainPane.setOnMouseMoved(event -> {
            coordsTextField.setText(
                    String.format("Coords: %s-%s", event.getX(), event.getY())
            );
            sceneCoordsTextField.setText(String.format(
                    "Scene coords: %s-%s", event.getSceneX(), event.getSceneY())
            );
            screenCoordsTextField.setText(String.format(
                    "Screen Coords: %s-%s", event.getScreenX(), event.getScreenY())
            );
        });

        final GBoard board = GBoard.getInstance();
        board.init(10, 8);
        for (GameCell cell : board.getAllCells(new ArrayList<>())) {
            int length = 60;
            final XY xy = cell.getXy();
            CellVisualizer rectangle = new CellVisualizer(length * xy.getX(), length * xy.getY(), length, length, cell);
            mainPane.getChildren().addAll(rectangle);
            cellToRectMap.put(cell, rectangle);
        }

        ScrollPane s1 = new ScrollPane();
        s1.setContent(mainPane);
        root.setCenter(s1);
        root.setLeft(vBox);
        Pane bottomPane = new BottomPane();
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setScene(scene);

        AbstractEvent.addListener(CreateUnitEvent.class, this);
        AbstractEvent.addListener(ShiftUnitEvent.class, this);
        AbstractEvent.addListener(ActionSelectionEvent.class, this);
        AbstractEvent.addListener(AbstractAction.FindPossibleAimsEvent.class, this);
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void doBeforeEvent(GEvent event) {

    }

    @Override
    public void doAfterEvent(GEvent event) {
        if (event instanceof CreateUnitEvent) {
            CreateUnitEvent createUnitEvent = (CreateUnitEvent) event;
            final GameCell place = createUnitEvent.getPlace();
            final Rectangle rectangle = cellToRectMap.get(place);
            final Pane parent = (Pane) rectangle.getParent();
            final XY_D center = getRectangleCenter(rectangle);
            final GObj obj = createUnitEvent.getObj();
            final UnitVisualizer visualizer = new UnitVisualizer(center.getX(), center.getY(), 20, obj);
            objToVisualizerMap.put(obj, visualizer);
            parent.getChildren().add(visualizer);
        } else if (event instanceof ShiftUnitEvent) {
            ShiftUnitEvent shiftUnitEvent = (ShiftUnitEvent) event;
            final GameCell toCell = shiftUnitEvent.getToCell();
            final Rectangle rectangle = cellToRectMap.get(toCell);
            final XY_D center = getRectangleCenter(rectangle);
            final UnitVisualizer visualizer = objToVisualizerMap.get(shiftUnitEvent.getObj());
            visualizer.setCenterX(center.getX());
            visualizer.setCenterY(center.getY());
        } else if (event instanceof ActionSelectionEvent) {
            ActionSelectionEvent actionSelectionEvent = (ActionSelectionEvent) event;
            actionNameField.setText(actionSelectionEvent.getAction().getClass().getSimpleName());
        } else if (event instanceof AbstractAction.FindPossibleAimsEvent) {
            AbstractAction.FindPossibleAimsEvent findPossibleAimsEvent = (AbstractAction.FindPossibleAimsEvent) event;
            final List<? extends PlaceHaving> possibleAims = findPossibleAimsEvent.getPossibleAims();
            for (PlaceHaving possibleAim : possibleAims) {
                if (possibleAim instanceof GObj) {
                    GObj aim = (GObj) possibleAim;
                    final UnitVisualizer visualizer = objToVisualizerMap.get(aim);
                    visualizer.showSelectionPossibility(findPossibleAimsEvent.getAction());
                }
            }
        }
    }

    private XY_D getRectangleCenter(Rectangle rectangle) {
        final double minX = rectangle.getBoundsInParent().getMinX();
        final double minY = rectangle.getBoundsInParent().getMinY();
        final double maxX = rectangle.getBoundsInParent().getMaxX();
        final double maxY = rectangle.getBoundsInParent().getMaxY();
        return new XY_D((minX + maxX) / 2, (minY + maxY) / 2);
    }
}
