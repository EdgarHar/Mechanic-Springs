package task3;

import util.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HorizontalStadiumBilliard {
    private static final int NUMBER_OF_REFLECTIONS = 10;
    private final double L;

    public HorizontalStadiumBilliard(double L) {
        this.L = L;
    }

    public List<Line> start() {
        List<Line> lines = new ArrayList<>();
        Point point = generatePoint();
        double px = Math.random();
        double py = Math.random();


        for (int i = 0; i < NUMBER_OF_REFLECTIONS; i++) {
            Line line = new Line(point, Point.addMomentum(point, px, py));
            Point intersectionWithLine = intersectionWithLine(line, px, py);

            if (Math.abs(intersectionWithLine.getX()) < L / 2) {
                lines.add(new Line(point, intersectionWithLine));
                point = intersectionWithLine;
                py = -py;
            } else {
                PointAndXc intersectionAndXc;

                if (intersectionWithLine.getX() >= L / 2) {
                    intersectionAndXc = intersectionWithRightSemiCircle(line, L);
                } else {
                    intersectionAndXc = intersectionWithLeftSemiCircle(line, L);
                }

                Point intersection = intersectionAndXc.getPoint();
                double xc = intersectionAndXc.getXc();

                lines.add(new Line(point, intersection));
                point = intersection;

                double a = Math.pow(point.getX(), 2) - Math.pow(point.getY() - xc, 2);
                double b = -2 * (point.getX() - xc) * point.getY();

                px = a * px + b * py;
                py = b * px - a * py;
            }
        }
        return lines;
    }

    private Point intersectionWithLine(Line line, double px, double py) {
        int y = py > 0 ? 1 : -1;
        if (px == 0.0) {
            return new Point(line.getStart().getX(), y);
        }
        double x = (y - line.getYIntercept()) / line.getSlope();
        return new Point(x, y);
    }

    private static PointAndXc intersectionWithRightSemiCircle(Line line, double L) {
        double k = line.getSlope();
        double b = line.getYIntercept();
        double c = Math.pow((2 * k * b - L), 2) - 4 * (1 + Math.pow(k, 2)) * (-1 + Math.pow(b, 2) + Math.pow(L / 2, 2));

        double x1 = (-(2 * k * b - L) + Math.sqrt(c)) / (2 * (1 + Math.pow(k, 2)));
        double x2 = (-(2 * k * b - L) - Math.sqrt(c)) / (2 * (1 + Math.pow(k, 2)));

        double y1 = k * x1 + b;
        double y2 = k * x2 + b;

        List<Point> intersections = List.of(new Point(x1, y1), new Point(x2, y2));
        return intersections.stream()
                .max(Comparator.comparingDouble(Point::getX))
                .map(p -> new PointAndXc(p, L / 2))
                .orElseThrow();
    }

    private static PointAndXc intersectionWithLeftSemiCircle(Line line, double L) {
        double k = line.getSlope();
        double b = line.getYIntercept();
        double a = 1 + Math.pow(k, 2);
        double bPrime = 2 * k * b + L;
        double cPrime = -1 + Math.pow(b, 2) + Math.pow(L / 2, 2);

        double discriminant = Math.pow(bPrime, 2) - 4 * a * cPrime;

        double x1 = (-bPrime + Math.sqrt(discriminant)) / (2 * a);
        double x2 = (-bPrime - Math.sqrt(discriminant)) / (2 * a);

        double y1 = k * x1 + b;
        double y2 = k * x2 + b;

        List<Point> intersections = List.of(new Point(x1, y1), new Point(x2, y2));
        return intersections.stream()
                .min(Comparator.comparingDouble(Point::getX))
                .map(p -> new PointAndXc(p, -L / 2))
                .orElseThrow();
    }

    private Point generatePoint() {
        return new Point(Math.random(), Math.random());
    }

    public static class Line {
        private Point start;
        private Point end;

        public Line(Point start, Point end) {
            this.start = Point.of(start);
            this.end = Point.of(end);
        }

        public Point getStart() {
            return Point.of(start);
        }

        public void setStart(Point start) {
            this.start = Point.of(start);
        }

        public Point getEnd() {
            return Point.of(end);
        }

        public void setEnd(Point end) {
            this.end = Point.of(end);
        }

        public double getSlope() {
            if (start.getX() == end.getX()) {
                return 0.0;
            }

            return (end.getY() - start.getY()) / (end.getX() - start.getX());
        }

        public double getYIntercept() {
            return start.getY() - getSlope() * start.getX();
        }
    }

    private static class PointAndXc {
        Point point;
        double xc;

        PointAndXc(Point point, double xc) {
            this.point = point;
            this.xc = xc;
        }

        public Point getPoint() {
            return point;
        }

        public double getXc() {
            return xc;
        }

    }
}
