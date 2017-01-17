package com.company;

import com.company.myPackage.Direction;
import com.company.myPackage.XY;

import java.text.DecimalFormat;

/**
 * Created by olko1016 on 11/7/2016.
 */
public class AngleTest {
    private static DecimalFormat df2 = new DecimalFormat("#.##");


    public static void main(String[] args) {
        System.out.println(Math.cos(90));
        for (Direction direction : Direction.CLOCKWISE_DIRECTIONS) {
            double r = direction.countRadius();
            System.out.println(direction);
            double acos = Math.acos(direction.getX() / r);
            if (direction.getY() < 0) {
                acos = 2 * Math.PI - acos;
            }
            System.out.println("Cos = " + df2.format(acos / Math.PI));
        }

        double angle = getAngleBetweenPoints(new XY(5, 5), new XY(2, 2));
        System.out.printf("Angle = " + angle);
    }

    private static double getAngleBetweenPoints(XY xy1, XY xy2) {
        double r = XY.getDistance(xy1, xy2);
        double result = Math.acos((xy2.getX() - xy1.getX()) / r);
        if (xy2.getY() < xy1.getY()) {
            result = 2 * Math.PI - result;
        }

        return result / Math.PI;
    }
}
