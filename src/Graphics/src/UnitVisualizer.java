import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import logic.AbstractAction;
import logic.GUnit;
import logic.GameCore;
import utils.XY_D;

import static javafx.scene.paint.Color.CHARTREUSE;

public class UnitVisualizer extends StackPane implements Visualizer {
    private final Image image;
    private final Circle circle;
    private final GUnit unit;
    private Timeline timeline;
    private final Label hpLabel = new Label();
    private UnitInfoPanel unitPanel;

    public UnitVisualizer(double x, double y, int r, GUnit unit) {
        this.unit = unit;
        image = ImageHelper.getImage(unit.getType());
        circle = new Circle(0, 0, r);
        circle.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        circle.setStrokeWidth(GraphicConstants.VISUALIZER_BORDER_SIZE);
        circle.setOnMouseClicked(o -> {
            unitPanel.setUnit(this.unit);
            GameCore.getInstance().press(unit);
        });

        hpLabel.setText(String.valueOf(unit.getHp()));
        hpLabel.setTranslateX(25);
        hpLabel.setTranslateY(25);
//        hpLabel.setTextFill(Color.INDIANRED);
        hpLabel.setId("hplabel");

        getChildren().addAll(circle, hpLabel);
        setLayoutX(x);
        setLayoutY(y);
        setBorder(createBorder(
                GraphicConstants.VISUALIZER_OUTER_BORDER_COLOR,
                GraphicConstants.VISUALIZER_OUTER_BORDER_SIZE
        ));
        setDefaults();
    }

    private Border createBorder(Color strokeColor, int borderWidth) {
        return new Border(
                new BorderStroke(
                        strokeColor,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(borderWidth)
                )
        );
    }

    @Override
    public void setDefaults() {
        if (timeline != null) {
            timeline.stop();
        }
        circle.setStroke(unit.getOwner().getColor());
        setBackground(Background.EMPTY);
    }

    @Override
    public void showSelectionPossibility(AbstractAction action) {
        setDefaults();
        if (action != null) {
            timeline = AnimationHelper.createStrokeAnimation(circle, Color.RED);
            timeline.play();
        }
        setBackground(new Background(new BackgroundFill(CHARTREUSE, null, null)));
    }

    public void moveTo(XY_D center) {
        setLayoutX(center.getX());
        setLayoutY(center.getY());
    }

    public void setUnitPanel(UnitInfoPanel unitPanel) {
        this.unitPanel = unitPanel;
    }

    public void setHp(int hp) {
        hpLabel.setText(String.valueOf(hp));
    }

    public void die() {
        ((Pane) getParent()).getChildren().remove(this);
    }
}

