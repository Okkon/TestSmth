package com.company.myPackage;

import com.company.myPackage.Events.UnitCreationEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Random;

public class Simulation extends Application {

    private final Random r = new Random();

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, ConstantClass.maxX, ConstantClass.maxY, Color.GRAY);
        stage.setTitle("JavaFX Scene Graph Demo");
        stage.setScene(scene);
        stage.show();

        drawField(root);
    }

    private void drawField(Group root) {
        GameModel model = GameModel.getInstance();
        WorldVisualizer worldVisualizer = WorldVisualizer.getInstance();
        worldVisualizer.setRoot(root);
        model.setWorldSize(ConstantClass.maxX, ConstantClass.maxY);

        for (int i = 0; i < 1; i++) {
            new UnitCreationEvent()
                    .setXy(OkHelper.getRandomXY())
                    .process();
        }

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(ConstantClass.sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    /*new UnitCreationEvent()
                          .setXy(new XY(
                                centerX + (maxX / k2) * (-k1 + r.nextInt(2 * k1)),
                                centerY + (maxY / k2) * (-k1 + r.nextInt(2 * k1))))
                          .process();*/
                    GameModel.getInstance().step();
                });
            }
        });
        thread.setDaemon(true);
        thread.start();
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
