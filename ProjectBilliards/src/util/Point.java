package util;

public class Point {

    private double x;

    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(Point point) {
        return new Point(point.getX(), point.getY());
    }
    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    public static Point addMomentum(Point p1, double px, double py) {
        return new Point(p1.x + px, p1.y + py);
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}