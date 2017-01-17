package com.company.myPackage;

/**
 * Created by olko1016 on 10/21/2016.
 */

public final class XY {
    private final double x;
    private final double y;

    public static XY get(double x, double y) {
        return new XY(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XY xy = (XY) o;

        if (x != xy.x) return false;
        return y == xy.y;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public XY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public XY changeX(double k) {
        return get(x + k, y);
    }

    public XY changeY(double k) {
        return get(x, y + k);
    }


    @Override
    public String toString() {
        return x + ":" + y;
    }

    public static double getDistance(XY p1, XY p2) {
        return Math.sqrt(
                Math.pow(p1.getX() - p2.getX(), 2)
                + Math.pow(p1.getY() - p2.getY(), 2)
        );
    }

    public XY step(Direction direction) {
        return new XY(x + direction.getX(), y + direction.getY());
    }

    public static XY step(XY currentPlace, Direction direction) {
        return new XY(currentPlace.x + direction.getX(), currentPlace.y + direction.getY());
    }

}
