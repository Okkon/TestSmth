public final class XY_D {
    private final double x;
    private final double y;

    public static XY_D get(double x, double y) {
        return new XY_D(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XY_D xy = (XY_D) o;

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

    public XY_D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public XY_D changeX(double k) {
        return get(x + k, y);
    }

    public XY_D changeY(double k) {
        return get(x, y + k);
    }


    @Override
    public String toString() {
        return x + ":" + y;
    }

    public static double getDistance(XY_D p1, XY_D p2) {
        return Math.sqrt(
                Math.pow(p1.getX() - p2.getX(), 2)
                + Math.pow(p1.getY() - p2.getY(), 2)
        );
    }

}
