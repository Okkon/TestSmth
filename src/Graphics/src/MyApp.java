import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.*;
import logic.actions.SelectUnitAction;
import logic.events.ActionSelectionEvent;
import logic.events.CreateObjEvent;
import logic.events.UnitDeathEvent;
import logic.events.UnitLoseHpEvent;
import utils.XY;
import utils.XY_D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApp extends Application implements GEventListener<GEvent> {

    //---------GRAPHIC------------
    private final Pane gameBoard = new Pane();
    private RightPanel rightPanel;
    //----------LOGIC--------
    private final GameCore gameCore = GameCore.getInstance();

    private final Map<GameCell, CellVisualizer> cellToVisualizerMap = new HashMap<>();
    private final Map<GObj, UnitVisualizer> objToVisualizerMap = new HashMap<>();

    private Stage primaryStage;
    private Scene scene;
    private Label notificationLabel;
    private BottomPanel bottomPanel;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simple Application");
        primaryStage.show();

        LeftPanel leftPanel = new LeftPanel();
        rightPanel = new RightPanel();
        bottomPanel = new BottomPanel();

        BorderPane root = new BorderPane();
        gameBoard.setId("board");
        gameBoard.setOnMouseMoved(event -> {
            leftPanel.showMousePositionInfo(event);
        });
        initBoard();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gameBoard);
        BorderPane centralPane = new BorderPane();
        centralPane.setCenter(scrollPane);
        String baseText = "I AM NOTIFICATION PANEL";
        notificationLabel = new Label(baseText);
        notificationLabel.setId("notification_label");
        notificationLabel.setOnMouseClicked(event -> notificationLabel.setText(baseText));
        notificationLabel.setPrefWidth(Double.MAX_VALUE);
        centralPane.setTop(notificationLabel);

        root.setCenter(centralPane);
        root.setLeft(leftPanel);
        root.setRight(rightPanel);
        root.setBottom(bottomPanel);

        scene = new Scene(root, GraphicConstants.WINDOW_WIDTH, GraphicConstants.WINDOW_HEIGHT);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);

        AbstractEvent.addListener(GEvent.class, this);
    }

    private void initBoard() {
        final GBoard board = GBoard.getInstance();
        board.init(STConst.BOARD_WIDTH, STConst.BOARD_HEIGHT);
        for (GameCell cell : board.getAllCells()) {
            int length = GraphicConstants.CELL_SIZE;
            int tap = GraphicConstants.CELL_TAP;
            final XY xy = cell.getXy();
            CellVisualizer cellVisualizer = new CellVisualizer(
                    tap + (length + tap) * xy.getX(),
                    tap + (length + tap) * xy.getY(),
                    length,
                    length,
                    cell
            );
            gameBoard.getChildren().addAll(cellVisualizer);
            cellToVisualizerMap.put(cell, cellVisualizer);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }


    private void log(Object s) {
        //System.out.println(s);
        bottomPanel.log(s);
    }

    @Override
    public void doBeforeEvent(GEvent event) {
        bottomPanel.openNode(event);
        log(event);
    }

    @Override
    public void doAfterEvent(GEvent event) {
        bottomPanel.closeNode(event);
//        log(event);
        /*---------------ActionSelectionEvent---------------------*/
        if (event instanceof AbstractAction.ActionPerformEvent) {
            AbstractAction.ActionPerformEvent performEvent = (AbstractAction.ActionPerformEvent) event;
            AbstractAction action = performEvent.getAction();
            if (action instanceof SelectUnitAction) {
                SelectUnitAction selectUnitAction = (SelectUnitAction) action;
                rightPanel.showUnitInfo(selectUnitAction.getAim());
            }
        }
        /*---------------ActionSelectionEvent---------------------*/
        if (event instanceof ActionSelectionEvent) {
            ActionSelectionEvent actionSelectionEvent = (ActionSelectionEvent) event;
            rightPanel.showActionSelection(actionSelectionEvent);
        } else {
            AnimationHelper.clearAnimations();
        }
        /*---------------CreateObjEvent---------------------*/
        if (event instanceof CreateObjEvent) {
            CreateObjEvent createObjEvent = (CreateObjEvent) event;
            final GameCell place = createObjEvent.getPlace();
            final Rectangle rectangle = cellToVisualizerMap.get(place);
            final Pane parent = (Pane) rectangle.getParent();
            final GObj obj = createObjEvent.getObj();
            GUnit unit = (GUnit) obj;
            final UnitVisualizer visualizer = new UnitVisualizer(
                    rectangle.getX(),
                    rectangle.getY(),
                    GraphicConstants.VISUALIZER_SIZE, unit
            );
            objToVisualizerMap.put(obj, visualizer);
            rightPanel.showUnitInfo(unit);
            parent.getChildren().add(visualizer);
            visualizer.create();
            /*-------ActionPerformEvent-----------*/
        } else if (event instanceof AbstractAction.ActionPerformEvent) {
            AbstractAction.ActionPerformEvent actionPerformEvent = (AbstractAction.ActionPerformEvent) event;
            bottomPanel.log("----------------------------");
            /*-------FailedSelectionAttemptEvent-----------*/
        } else if (event instanceof FailedSelectionAttemptEvent) {
            FailedSelectionAttemptEvent selectionAttemptEvent = (FailedSelectionAttemptEvent) event;
            notificationLabel.setText(selectionAttemptEvent.toString());
            /*-------UnitLoseHpEvent-----------*/
        } else if (event instanceof UnitLoseHpEvent) {
            UnitLoseHpEvent unitLoseHpEvent = (UnitLoseHpEvent) event;
            GUnit unit = unitLoseHpEvent.getUnit();
            UnitVisualizer unitVisualizer = objToVisualizerMap.get(unit);
            unitVisualizer.setHp(unitLoseHpEvent.getRemainedHp());
            /*-------UnitDeathEvent-----------*/
        } else if (event instanceof UnitDeathEvent) {
            UnitDeathEvent unitDeathEvent = (UnitDeathEvent) event;
            GUnit unit = unitDeathEvent.getUnit();
            UnitVisualizer unitVisualizer = objToVisualizerMap.get(unit);
            unitVisualizer.die();
            /*-------ShiftUnitEvent-----------*/
        } else if (event instanceof ShiftObjectEvent) {
            ShiftObjectEvent shiftUnitEvent = (ShiftObjectEvent) event;
            final GameCell toCell = shiftUnitEvent.getToCell();
            final Rectangle rectangle = cellToVisualizerMap.get(toCell);
            final UnitVisualizer visualizer = objToVisualizerMap.get(shiftUnitEvent.getObj());
            visualizer.moveTo(getRectangleCoords(rectangle));
            /*---------------AimChoseEvent---------------------*/
        } else if (event instanceof AbstractAction.AimChoseEvent) {
            AbstractAction.AimChoseEvent aimChoseEvent = (AbstractAction.AimChoseEvent) event;
            rightPanel.showAimChoose(aimChoseEvent);
            ActionAimRequirement requirement = aimChoseEvent.getAimRequirement();
            final List possibleAims = gameCore.findAims(requirement);
            if (requirement.getFilters().get(0).equals(ClassFilter.getInstance(UnitType.class))) {
                final Stage dialog = createDialog();
                dialog.initModality(Modality.NONE);
                UnitTypeSelector unitTypeSelector = new UnitTypeSelector(BaseTypes.getTypes(), dialog, aimChoseEvent.getAction());
                //TODO: add selector close on selectActionEvent
                dialog.show();
            }
//            AnimationHelper.clearAnimations();
            for (Object possibleAim : possibleAims) {
                if (possibleAim instanceof GObj) {
                    GObj aim = (GObj) possibleAim;
                    final UnitVisualizer visualizer = objToVisualizerMap.get(aim);
                    visualizer.showSelectionPossibility(aimChoseEvent.getAction());
                    AnimationHelper.addAnimatedVisualizer(visualizer);
                } else if (possibleAim instanceof GameCell) {
                    GameCell aim = (GameCell) possibleAim;
                    final CellVisualizer visualizer = cellToVisualizerMap.get(aim);
                    visualizer.showSelectionPossibility(aimChoseEvent.getAction());
                    AnimationHelper.addAnimatedVisualizer(visualizer);
                }
            }
        }
    }

    @Override
    public double getPriority() {
        return STConst.APP_LISTENER_PRIORITY;
    }

    private XY_D getRectangleCoords(Rectangle rectangle) {
        return new XY_D(rectangle.getX(), rectangle.getY());
    }

    private Stage createDialog() {
        final Stage dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setResizable(false);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(scene.getWindow());
        return dialog;
    }

}
