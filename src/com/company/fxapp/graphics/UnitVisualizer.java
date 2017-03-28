package com.company.fxapp.graphics;

import com.company.fxapp.core.GObj;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UnitVisualizer extends Circle {
    private final GObj obj;

    public UnitVisualizer(double x, double y, int r, GObj obj) {
        super(x, y, r);
        this.obj = obj;
        setFill(Color.GOLD);
    }
}
