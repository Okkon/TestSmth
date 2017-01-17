package com.company;

import com.company.myPackage.Board;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private final Random r = new Random();

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.GRAY);
        stage.setTitle("JavaFX Scene Graph Demo");
        stage.setScene(scene);
        stage.show();

        drawField(root);
    }

    private void drawField(Group root) {
        Board board = new Board(5, 5);

        final int centerX = 50;
        final int centerY = 50;
        final int radius = 50;
        final int anglesCount = 6;
        final double sideLength = radius * Math.sin(Math.PI * 2 / anglesCount);

        final int columns = 4;
        final int rows = 6;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final Polygon hex = createHex(
                        centerX + (radius * 1.5) * i,
                        centerY + 2 * sideLength * j + i % 2 * sideLength,
                        radius,
                        anglesCount
                );
                hex.setFill(Color.GRAY);
                hex.setStroke(Color.BLACK);
                hex.setOnMouseEntered(event -> ((Polygon) event.getSource()).setFill(Color.YELLOW));
                hex.setOnMouseExited(event -> ((Polygon) event.getSource()).setFill(Color.GRAY));
                root.getChildren().add(hex);
//                addRandomEffect(hex);
            }
        }

    }

    private void addRandomEffect(Polygon hex) {
        List<Effect> list = Arrays.asList(
                new Blend(),
                new Bloom(),
                new BoxBlur(),
                new ColorAdjust(),
                new ColorInput(),
                new DisplacementMap(),
                new DropShadow(),
                new GaussianBlur(),
                new Glow(),
                new ImageInput(),
                new InnerShadow(),
                new Lighting(),
                new MotionBlur(),
                new PerspectiveTransform(),
                new Reflection(),
                new SepiaTone(),
                new Shadow()
        );
        Effect randomEffect = list.get(r.nextInt(list.size()));
        hex.setEffect(randomEffect);
        hex.setAccessibleText(randomEffect.getClass().getSimpleName());
    }

    private Polygon createHex(double centerX, double centerY, int radius, int angle) {
        final Polygon e = new Polygon();
        for (int i = 0; i <= angle; i++) {
            e.getPoints().addAll(
                    Math.cos(Math.PI * 2 * i / angle) * radius + centerX,
                    Math.sin(Math.PI * 2 * i / angle) * radius + centerY
            );
        }
        return e;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
