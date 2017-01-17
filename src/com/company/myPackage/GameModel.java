package com.company.myPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olko1016 on 10/21/2016.
 */
public class GameModel {
    private static GameModel INSTANCE = new GameModel();

    private GameModel() {
    }

    ;

    public static GameModel getInstance() {
        return INSTANCE;
    }


    List<GObject> objectsList = new ArrayList();
    private double x;
    private double y;

    public void setWorldSize(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void addObject(GObject gObject) {
        objectsList.add(gObject);
    }

    public void step() {
        for (GObject gObject : objectsList) {
            gObject.step();
        }
    }
}
