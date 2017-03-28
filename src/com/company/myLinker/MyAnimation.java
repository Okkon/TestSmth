package com.company.myLinker;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MyAnimation extends Application {
    private final int windowWidth = 600;
    private final int windowHeight = 600;

    private final int upPad = 100;
    private final int leftPad = 100;
    private final int bodyHeight = 100;
    private final int bodyWidth = 50;
    private final int headHeight = bodyWidth / 2;
    private final Group group = new Group();
    private final double armLength = bodyHeight;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simple Application");
        primaryStage.show();

        BorderPane root = new BorderPane();

        /*GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #333366;");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));*/

        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-background-color: #336699;");

        vBox.getChildren().addAll(new Button("button 1"), new Button("button 2"));

        group.setStyle("-fx-background-color: #333366;");

        Joint upperLeftJoint = new Joint(leftPad, upPad, 5, 0);
        Joint upperRightJoint = new Joint(leftPad + bodyWidth, upPad, 5, 0);
        Joint lowerLeftJoint = new Joint(leftPad, upPad + bodyHeight, 5, 0);
        Joint lowerRightJoint = new Joint(leftPad + bodyWidth, upPad + bodyHeight, 5, 0);
        setJointParams(upperLeftJoint, upperRightJoint, lowerLeftJoint, lowerRightJoint);

        Rectangle rectangle = new Rectangle(leftPad, upPad, bodyWidth, bodyHeight);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);
        Circle circle = new Circle(leftPad + bodyWidth / 2, upPad - headHeight, headHeight);
        circle.setStroke(Color.BLACK);
        circle.setFill(null);

        group.getChildren().addAll(rectangle, circle);
        group.getChildren().addAll(upperLeftJoint, upperRightJoint, lowerLeftJoint, lowerRightJoint);

        root.setCenter(group);
        root.setRight(vBox);

        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setScene(scene);
    }

    private void setJointParams(Joint... joints) {
        for (Joint joint : joints) {
            joint.setFill(Color.AZURE);
//            joint.setStrokeWidth(2);
//            joint.setStroke(Color.YELLOW);

            Limb limb = new Limb(joint.getCenterX(), joint.getCenterY(), joint.getCenterX(), joint.getCenterY() + armLength);
            limb.setStyle("-fx-stroke: gray; -fx-stroke-width: 3;");
            limb.setOnMouseDragged((MouseEvent event) -> {
                double mouseDeltaX = event.getSceneX() - joint.getTranslateX();
                double mouseDeltaY = event.getSceneY() - joint.getTranslateY();
                double radAngle = Math.atan2(mouseDeltaY, mouseDeltaX);
                double[] res = rotateLine(joint, radAngle - Math.toRadians(lineCurrentAngle(limb)), limb.getEndX(), limb.getEndY());

                limb.setEndX(res[0]);
                limb.setEndY(res[1]);
            });

            joint.setControlledLimb(limb);
            joint.setOnMousePressed(event -> {
                double stepSize = 5D;
                joint.changeAngle(MouseButton.PRIMARY.equals(event.getButton()) ? stepSize : -stepSize);
//                joint.getLimb().setRotate(joint.getAngle());
                double angle = joint.getAngle();
                System.out.println("angle = " + angle);
                Line jointLimb = joint.getLimb();
                double endX0 = jointLimb.getStartX();
                double endY0 = jointLimb.getStartY();
                jointLimb.getTransforms().clear();
                jointLimb.getTransforms().add(new Rotate(joint.getAngle(), joint.getCenterX(), joint.getCenterY()));
                double endX1 = jointLimb.getEndX();
                double endY1 = jointLimb.getEndY();

                System.out.println(String.format("end moved from: %s:%s to %s:%s", endX0, endY0, endX1, endY1));
            });
            group.getChildren().add(limb);
        }
    }

    private double[] rotateLine(Shape pivot, double radAngle, double endX, double endY) {
        double x, y;
        x = Math.cos(radAngle) * (endX - pivot.getTranslateX()) - Math.sin(radAngle) * (endY - pivot.getTranslateY()) + pivot.getTranslateX();
        y = Math.sin(radAngle) * (endX - pivot.getTranslateX()) + Math.cos(radAngle) * (endY - pivot.getTranslateY()) + pivot.getTranslateY();
        return new double[]{x, y};
    }

    private double lineCurrentAngle(Limb limb) {
        Joint joint = limb.getControllingJoint();
        return Math.toDegrees(Math.atan2(limb.getEndY() - joint.getTranslateY(), limb.getEndX() - joint.getTranslateX()));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
