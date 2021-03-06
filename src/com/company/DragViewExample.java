package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DragViewExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField tf = new TextField("Drag from here");
        Label label = new Label("Drop here");
        tf.setOnDragDetected(e -> {
            Dragboard db = tf.startDragAndDrop(TransferMode.COPY);
            db.setDragView(new Text(tf.getText()).snapshot(null, null), e.getX(), e.getY());
            ClipboardContent cc = new ClipboardContent();
            cc.putString(tf.getText());
            db.setContent(cc);
        });
        label.setOnDragOver(e -> {
            e.acceptTransferModes(TransferMode.COPY);
        });
        label.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {
                label.setText(db.getString());
                e.setDropCompleted(true);
            } else {
                e.setDropCompleted(false);
            }
        });

        Scene scene = new Scene(new StackPane(new HBox(10, tf, label)), 350, 75);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}