package com.company.myPackage;

import javafx.scene.Group;
import javafx.scene.shape.Shape;


public class WorldVisualizer {
    private static WorldVisualizer INSTANCE = new WorldVisualizer();

    private WorldVisualizer() {
    }

    ;

    public static WorldVisualizer getInstance() {
        return INSTANCE;
    }


    private Group root;

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public void addChild(Shape shape) {
        root.getChildren().add(shape);
    }

}
