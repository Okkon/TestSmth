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
import logic.*;
import utils.XY;
import utils.XY_D;

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
    private final HashMap<GameCell, CellVisualizer> cellToVisualizerMap = new HashMap<>();
    private final HashMap<GObj, UnitVisualizer> objToVisualizerMap = new HashMap<>();

    private VBox actionInfoBox;
    private VBox aimsBox;
    private Label actionNameLabel = new Label();


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simple Application");
        primaryStage.show();

        VBox infoBox = new VBox(5);
        infoBox.setStyle("-fx-background-color: #336699;");
        TextField coordsTextField = new TextField("test text");
        TextField sceneCoordsTextField = new TextField("test text");
        TextField screenCoordsTextField = new TextField("test text");

        final ObservableList<Node> children = infoBox.getChildren();
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

        actionInfoBox = new VBox(15);
        actionInfoBox.setStyle("-fx-background-color: #336699;");

        aimsBox = new VBox(15);

        actionInfoBox.getChildren().addAll(
                new Label("Selected Action:"),
                actionNameLabel,
                aimsBox
        );

        mainPane.setStyle("-fx-border-color: orange; -fx-border-width: 20; -fx-background-color: #333366;");
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
        for (GameCell cell : board.getAllCells()) {
            int length = 60;
            int tap = 5;
            final XY xy = cell.getXy();
            CellVisualizer cellVisualizer = new CellVisualizer(
                    tap + (length + tap) * xy.getX(),
                    tap + (length + tap) * xy.getY(),
                    length,
                    length,
                    cell
            );
            mainPane.getChildren().addAll(cellVisualizer);
            cellToVisualizerMap.put(cell, cellVisualizer);
        }

        ScrollPane s1 = new ScrollPane();
        s1.setContent(mainPane);
        root.setCenter(s1);
        root.setLeft(infoBox);
        root.setRight(actionInfoBox);
        Pane bottomPane = new BottomPane();
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setScene(scene);

        AbstractEvent.addSuperListener(this);
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void doBeforeEvent(GEvent event) {
        System.out.println(event);
    }

    @Override
    public void doAfterEvent(GEvent event) {
        if (event instanceof ActionSelectionEvent) {
            ActionSelectionEvent actionSelectionEvent = (ActionSelectionEvent) event;
            actionNameField.setText(actionSelectionEvent.getAction().getClass().getSimpleName());
            actionNameLabel.setText(actionSelectionEvent.getAction().getClass().getSimpleName());
            List<ActionAim> aims = ((ActionSelectionEvent) event).getAction().getAims();
            ObservableList<Node> children = aimsBox.getChildren();
            children.clear();
            for (ActionAim aim : aims) {
                children.add(new Label(aim.toString()));
            }
        } else {
            AnimationHelper.clearAnimations();
        }
        /*---------------logic.CreateObjEvent---------------------*/
        if (event instanceof CreateObjEvent) {
            CreateObjEvent createObjEvent = (CreateObjEvent) event;
            final GameCell place = createObjEvent.getPlace();
            final Rectangle rectangle = cellToVisualizerMap.get(place);
            final Pane parent = (Pane) rectangle.getParent();
            final XY_D center = getRectangleCenter(rectangle);
            final GObj obj = createObjEvent.getObj();
            final UnitVisualizer visualizer = new UnitVisualizer(center.getX(), center.getY(), 20, obj);
            objToVisualizerMap.put(obj, visualizer);
            parent.getChildren().add(visualizer);
            /*-------ShiftUnitEvent-----------*/
        } else if (event instanceof ShiftObjectEvent) {
            ShiftObjectEvent shiftUnitEvent = (ShiftObjectEvent) event;
            final GameCell toCell = shiftUnitEvent.getToCell();
            final Rectangle rectangle = cellToVisualizerMap.get(toCell);
            final XY_D center = getRectangleCenter(rectangle);
            final UnitVisualizer visualizer = objToVisualizerMap.get(shiftUnitEvent.getObj());
            visualizer.setCenterX(center.getX());
            visualizer.setCenterY(center.getY());
            /*---------------AimSelectionEvent---------------------*/
        } else if (event instanceof AbstractAction.AimSelectionEvent) {
            AbstractAction.AimSelectionEvent aimSelectionEvent = (AbstractAction.AimSelectionEvent) event;
            final List possibleAims = gameCore.findAims(aimSelectionEvent.getAimRequirement());
//            AnimationHelper.clearAnimations();
            for (Object possibleAim : possibleAims) {
                if (possibleAim instanceof GObj) {
                    GObj aim = (GObj) possibleAim;
                    final UnitVisualizer visualizer = objToVisualizerMap.get(aim);
                    visualizer.showSelectionPossibility(aimSelectionEvent.getAction());
                    AnimationHelper.addAnimatedVisualizer(visualizer);
                } else if (possibleAim instanceof GameCell) {
                    GameCell aim = (GameCell) possibleAim;
                    final CellVisualizer visualizer = cellToVisualizerMap.get(aim);
                    visualizer.showSelectionPossibility(aimSelectionEvent.getAction());
                    AnimationHelper.addAnimatedVisualizer(visualizer);
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
