package com.company.myLinker;

import com.company.AngleTest;
import com.company.fxapp.utils.XY_D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.ENTER;

/**
 * @author melkhaldi
 */
public class RotateLine extends Application {
    Circle pivot;
    Line line;

    @Override
    public void start(Stage primaryStage) {
        //make a visal representation of rotation center
        pivot = new Circle(0, 0, 8);
        pivot.setTranslateX(50);
        pivot.setTranslateY(50);

        //draw a red line (rotated)--using pivot location as start point.
        line = new Line(50, 50, 200, 200);
        line.setStyle("-fx-stroke: red; -fx-stroke-width: 3;");

        //horizontal line (fixed).
        Line horizontaLine = new Line(50, 50, 200, 50);
        horizontaLine.setStrokeWidth(1);

        //label to display angle between mouse cursor and horizontal line.
        String prefixMouse = "Angle <Mouse -Pivot - Horizontal Line> is: ";
        Label mouseAngleReporter = new Label(prefixMouse);
        mouseAngleReporter.setLayoutY(300);

        //label to display angle between rotated red line, pivot, and hosizontal fixed line.
        String prefixLine = "Angle <Red Line -Pivot - Horizontal Line> is: ";
        Label lineAngleReporter = new Label(prefixLine);
        lineAngleReporter.setLayoutY(400);

        TextField rotateField = new TextField("Enter degree angle and hit enter");
        rotateField.setLayoutY(200);
        //add to root and scene
        AnchorPane root = new AnchorPane();
        root.getChildren().add(line);
        root.getChildren().add(horizontaLine);
        root.getChildren().add(pivot);
        root.getChildren().add(mouseAngleReporter);
        root.getChildren().add(lineAngleReporter);
        root.getChildren().add(rotateField);
        Scene scene = new Scene(root, 450, 450);

        primaryStage.setTitle("Rotate Line");
        primaryStage.setScene(scene);
        primaryStage.show();

        // set on mouse drag
        line.setOnMouseDragged((MouseEvent event) -> {
            double mouseDeltaX = event.getSceneX() - pivot.getTranslateX();
            double mouseDeltaY = event.getSceneY() - pivot.getTranslateY();
            double radAngle = Math.atan2(mouseDeltaY, mouseDeltaX);
            double[] res = rotateLine(pivot, radAngle - Math.toRadians(lineCurrentAngle()), line.getEndX(), line.getEndY());

            line.setEndX(res[0]);
            line.setEndY(res[1]);

            mouseAngleReporter.setText(prefixMouse + Math.toDegrees(radAngle));
            lineAngleReporter.setText(prefixLine + lineCurrentAngle());
        });

        rotateField.setOnKeyPressed((e) -> {
            if (e.getCode().equals(ENTER)) {
                if (rotateField.getText().matches("\\b\\d+\\b")) {
                    //subtract line current angle to make rotation absolute
                    double radAngle = Math.toRadians(Double.valueOf(rotateField.getText()) - lineCurrentAngle());

                    double[] res = rotateLine(pivot, radAngle, line.getEndX(), line.getEndY());

                    line.setEndX(res[0]);
                    line.setEndY(res[1]);
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private double lineCurrentAngle() {
        return AngleTest.getAngleBetweenPoints(new XY_D(line.getEndX(), line.getEndY()), new XY_D(pivot.getTranslateX(), pivot.getTranslateY()));
//        return Math.toDegrees(Math.atan2(line.getEndY() - pivot.getTranslateY(), line.getEndX() - pivot.getTranslateX()));
    }

    private double[] rotateLine(Shape pivot, double radAngle, double endX, double endY) {
        double x, y;
        x = Math.cos(radAngle) * (endX - pivot.getTranslateX()) - Math.sin(radAngle) * (endY - pivot.getTranslateY()) + pivot.getTranslateX();
        y = Math.sin(radAngle) * (endX - pivot.getTranslateX()) + Math.cos(radAngle) * (endY - pivot.getTranslateY()) + pivot.getTranslateY();
        return new double[]{x, y};
    }

}