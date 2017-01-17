package com.company.myPackage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by olko1016 on 12/26/2016.
 */
public class CircleVisualizer implements ObjectVisualizer {
    private Circle circle;

    public CircleVisualizer(XY xy) {
        Color circleColor = OkHelper.getRandomColor();
        this.circle = new Circle(xy.getX(), xy.getY(), 15, circleColor);
        circle.setOnMousePressed(event -> {
            circle.setFill(OkHelper.getRandomColor());
        });
        WorldVisualizer.getInstance().addChild(circle);
    }

    @Override
    public void shift(XY shiftDirection) {
        circle.setCenterX(circle.getCenterX() + shiftDirection.getX());
        circle.setCenterY(circle.getCenterY() + shiftDirection.getY());
    }

    @Override
    public void setDirection(double direction) {

    }
}
