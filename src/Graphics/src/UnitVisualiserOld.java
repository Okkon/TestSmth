import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.util.Duration;
import logic.*;

@Deprecated
public class UnitVisualiserOld {
    private static final Duration MOVE_ANIMATION_DURATION = Duration.ONE;
    private static final Duration ANIMATION_DURATION = Duration.ONE;
    private Shape token;
    private final Label hpLabel = new Label();
    private final StackPane pane = new StackPane();
    private final GObj obj;
    private Image image;
    private final int OBJECT_VISUALIZER_SIZE = 50;

    public UnitVisualiserOld(final GObj obj/*, GamePanel gamePanel*/) {
        this.obj = obj;
        final int size = OBJECT_VISUALIZER_SIZE;
        pane.setFocusTraversable(false);
        hpLabel.setPrefSize(10, 10);
        if (obj instanceof GUnit) {
            GUnit unit = (GUnit) obj;
            token = new Circle(size / 2);
            token.getStyleClass().add("unit");
            image = ImageHelper.getImage(unit.getType());
            token.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
            hpLabel.setText(String.valueOf(unit.getHp()));
            setReady(unit.getMp() > 0);
        }
        /*if (obj instanceof Tower) {
            Tower tower = (Tower) obj;
            token = new Rectangle(size, size);
            Image img = ImageHelper.getImage(tower, tower instanceof MainTower);
            token.setFill(new ImagePattern(img, 0, 0, 1, 1, true));
            token.getStyleClass().add("tower");
        }*/
        token.setOnMousePressed(mouseEvent -> GameCore.getInstance().press(obj));
    }

    public void changePlace(GameCell currentCell, GameCell cellToGo) {
        final Bounds bounds1 = getBounds(currentCell);
        final Bounds bounds2 = getBounds(cellToGo);

        Path path = new Path();
        final double w = bounds1.getWidth() / 2;
        final double h = bounds1.getHeight() / 2;
        path.getElements().addAll(
                new MoveTo(bounds1.getMinX() + w, bounds1.getMinY() + h),
                new LineTo(bounds2.getMinX() + w, bounds2.getMinY() + h)
        );

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(MOVE_ANIMATION_DURATION);
        pathTransition.setNode(pane);
        pathTransition.setPath(path);

        GraphicsHelper.getInstance().addTransition(pathTransition);
    }

    private Bounds getBounds(GameCell currentCell) {
        return null;
    }

    public void die(GameCell place) {
        FadeTransition transition = new FadeTransition();
        transition.setDuration(ANIMATION_DURATION);
        transition.setNode(pane);
        transition.setFromValue(100);
        transition.setToValue(0);
//        transition.setOnFinished(actionEvent -> GraphicsHelper.getInstance().remove(pane));
        GraphicsHelper.getInstance().addTransition(transition);
    }

    public void setPlayer(Player player) {
        final Color color = player.getColor();
        token.setStroke(color);
    }

    public void setReady(boolean isReady) {
        final ObservableList<String> styleClass = token.getStyleClass();
        styleClass.remove("ready");
        if (isReady) {
            styleClass.add("ready");
        }
    }

    public void create(GameCell gameCell) {
        final Bounds b = getBounds(gameCell);
        final double w = (b.getWidth() - OBJECT_VISUALIZER_SIZE) / 2;
        final double h = (b.getHeight() - OBJECT_VISUALIZER_SIZE) / 2;
        pane.setTranslateX(b.getMinX() + w);
        pane.setTranslateY(b.getMinY() + h);
        pane.getChildren().addAll(token, hpLabel);
        hpLabel.setTranslateX(25);
        hpLabel.setTranslateY(25);
        setPlayer(obj.getOwner());
//        GraphicsHelper.getInstance().add(pane);

        ScaleTransition transition = new ScaleTransition();
        transition.setNode(pane);
        transition.setDuration(ANIMATION_DURATION.divide(1d));
        final double sizeChange = 1d;
        transition.setFromX(0);
        transition.setFromY(0);
        transition.setToX(sizeChange);
        transition.setToY(sizeChange);
        GraphicsHelper.getInstance().addTransition(transition);
    }

    public void setSelected(boolean b) {
        if (b) {
            token.getStyleClass().add("selected");
        } else {
            token.getStyleClass().remove("selected");
        }
    }

    public void changeHP(final int hp) {
        ScaleTransition transition = new ScaleTransition();
        transition.setNode(hpLabel);
        transition.setDuration(ANIMATION_DURATION.divide(3d));
        final double sizeChange = 2d;
        transition.setToX(sizeChange);
        transition.setToY(sizeChange);
        transition.setOnFinished(actionEvent -> hpLabel.setText(String.valueOf(hp)));
        ScaleTransition transition2 = new ScaleTransition();
        transition2.setNode(hpLabel);
        transition2.setDuration(ANIMATION_DURATION.divide(3d));
        transition2.setToX(1d);
        transition2.setToY(1d);
        GraphicsHelper.getInstance().addTransition(transition);
        GraphicsHelper.getInstance().addTransition(new PauseTransition(ANIMATION_DURATION.divide(3d)));
        GraphicsHelper.getInstance().addTransition(transition2);
    }

    /*public void startAttack(Hit hit) {
        UnitVisualiserOld visualizer = (UnitVisualiserOld) hit.getAim().getVisualizer();
        XY c = UIHelper.getCenter(this);
        XY c2 = UIHelper.getCenter(visualizer);

        final Shape weapon = createWeapon(hit);

        GraphicsHelper.getInstance().add(weapon);
        Path path = PathBuilder.create()
                .elements(
                        new MoveTo(c.getX(), c.getY()),
                        new LineTo(c2.getX(), c2.getY()))
                .build();

        PathTransition pathTransition = PathTransitionBuilder.create()
                .node(weapon)
                .duration(MyConst.MOVE_ANIMATION_DURATION)
                .path(path)
                .autoReverse(true)
                .cycleCount(2)
                .orientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT)
                .onFinished(actionEvent -> GraphicsHelper.getInstance().remove(weapon)).build();
        GraphicsHelper.getInstance().addTransition(pathTransition);
    }*/

    /*private Shape createWeapon(Hit hit) {
        double h = pane.getHeight();
        double w = pane.getWidth();
        double swordLength = 0.6;
        double swordPosition = 0.5;
        double leverPosition = 0.3;
        double leverWidth = 0.1;
        double gap = (1 - swordLength) / 2;
        final Shape sword = new Polyline(
                w * (gap),
                h * swordPosition,

                w * (1 - gap),
                h * swordPosition,

                w * (gap + leverPosition * swordLength),
                h * swordPosition,

                w * (gap + leverPosition * swordLength),
                h * (swordPosition + leverWidth),

                w * (gap + leverPosition * swordLength),
                h * (swordPosition - leverWidth)
        );
        if (DamageType.MAGIC.equals(hit.getDamageType())) {
            sword.setStroke(Color.BLUEVIOLET);
        }
        sword.setStrokeWidth(3);
        return sword;
    }*/

    public Image getImage() {
        return image;
    }

    public void changeOwner(Player newOwner) {
        ScaleTransition transition = new ScaleTransition();
        transition.setNode(token);
        transition.setDuration(ANIMATION_DURATION.divide(3d));
        final double sizeChange = 2d;
        transition.setToX(sizeChange);
        transition.setToY(sizeChange);
        StrokeTransition strokeTransition = new StrokeTransition(
                ANIMATION_DURATION.divide(3d),
                this.token
        );
        strokeTransition.setToValue(newOwner.getColor());
        ScaleTransition transition2 = new ScaleTransition();
        transition2.setNode(token);
        transition2.setDuration(ANIMATION_DURATION.divide(3d));
        transition2.setToX(1d);
        transition2.setToY(1d);
        GraphicsHelper.getInstance().addTransition(transition);
        GraphicsHelper.getInstance().addTransition(strokeTransition);
        GraphicsHelper.getInstance().addTransition(transition2);
    }
}
