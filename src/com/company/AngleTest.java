package com.company;

import com.company.fxapp.utils.XY_D;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
//import org.jbox2d.dynamics.Body;

/**
 * Created by olko1016 on 11/7/2016.
 */
public class AngleTest {
    private static DecimalFormat df2 = new DecimalFormat("#.##");


    public static void main(String[] args) {
        List<XY_D> places = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == j && j == 0) {
                    continue;
                }
                places.add(new XY_D(i, j));
            }
        }
        XY_D xy = new XY_D(0, 0);
        for (XY_D place : places) {
            System.out.println(place + " :  " + getAngleBetweenPointsInPi(xy, place));
        }
    }

    public static double getAngleBetweenPointsInPi(XY_D xy1, XY_D xy2) {
        double r = XY_D.getDistance(xy1, xy2);
        double result = Math.acos((xy2.getX() - xy1.getX()) / r);
        if (xy2.getY() < xy1.getY()) {
            result = 2 * Math.PI - result;
        }

        return result / Math.PI;
    }

    public static double getAngleBetweenPoints(XY_D xy1, XY_D xy2) {
        double r = XY_D.getDistance(xy1, xy2);
        double result = Math.acos((xy2.getX() - xy1.getX()) / r);
        if (xy2.getY() < xy1.getY()) {
            result = 2 * Math.PI - result;
        }

        return result;
    }
}
