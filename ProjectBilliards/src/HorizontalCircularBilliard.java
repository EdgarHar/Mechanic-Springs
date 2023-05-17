import java.util.ArrayList;
import java.util.List;

public class HorizontalCircularBilliard {

    private int numberOfReflections;

    public HorizontalCircularBilliard(int numberOfReflections) {
        this.numberOfReflections = numberOfReflections;
    }

    public List<Point> start() {
        List<Point> points = new ArrayList<>();
        //generate random points
        Point point = generatePoint();
        double px = Math.random();
        double py = Math.random();

        for (int i = 0; i < numberOfReflections; i++) {
            points.add(point);

            //get next positions
            double nextX = point.getX() + px;
            double nextY = point.getY() + py;
            double dist = Math.sqrt(Math.pow(nextX, 2) + Math.pow(nextY, 2));

            point.setX(nextX / dist);
            point.setY(nextY / dist);

            //determine new momentum by provided formula
            px = (nextY * nextY - nextX * nextX) * px - 2 * nextX * nextY * py;
            py = -2 * nextX * nextY * px + (nextX * nextX - nextY * nextY) * py;
        }
        return points;
    }

    public boolean isReversible(List<Point> points, double delta) {
        Point point = points.get(numberOfReflections - 1);
        double x = point.getX();
        double y = point.getY();
        //traverse the list backwards to go reverse
        for (int i = numberOfReflections - 1; i >= 0; i--) {
            //take reverse of the momentum
            double px = -x;
            double py = -y;

            //get next positions
            double nextX = x + px;
            double nextY = y + py;
            double dist = Math.sqrt(Math.pow(nextX, 2) + Math.pow(nextY, 2));

            //check whether the path has deviated by some delta, if so, return the number of reflections it took for that to happen
            if (Math.abs(nextX - point.getX()) > delta || Math.abs(nextY - point.getY()) > delta) {
                System.out.println("It will take " + i + " reflections for the path to deviate");
                return false;
            }

            //set new coordinates
            point.setX(nextX / dist);
            point.setY(nextY / dist);

            //determine new momentum by provided formula
            px = (nextY * nextY - nextX * nextX) * px - 2 * nextX * nextY * py;
            py = -2 * nextX * nextY * px + (nextX * nextX - nextY * nextY) * py;
        }
        return true;
    }

    private Point generatePoint() {
        return new Point(Math.random(), Math.random());
    }
}
