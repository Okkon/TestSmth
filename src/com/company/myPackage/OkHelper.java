package com.company.myPackage;

import javafx.scene.paint.Color;

import java.util.Random;

public abstract class OkHelper {
    private static final Random r = new Random();

    public static Color getRandomColor() {
        return Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble());
    }

    public static XY getRandomXY() {
        return new XY(
                r.nextDouble() * ConstantClass.maxX,
                r.nextDouble() * ConstantClass.maxY
        );
    }

    public static double getAngleBetweenPointsInPi(XY xy1, XY xy2) {
        return getAngleBetweenPoints(xy1, xy2) / Math.PI;
    }

    public static double getAngleBetweenPoints(XY xy1, XY xy2) {
        double r = XY.getDistance(xy1, xy2);
        double result = Math.acos((xy2.getX() - xy1.getX()) / r);
        if (xy2.getY() < xy1.getY()) {
            result = 2 * Math.PI - result;
        }

        return result;
    }
}