import java.util.List;

public class Main {
    public static void main(String[] args) {
        HorizontalCircularBilliard horizontalCircularBilliard = new HorizontalCircularBilliard(10);
        List<Point> points = horizontalCircularBilliard.start();
    }
}