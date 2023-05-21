import task1.HorizontalCircularBilliard;
import task2.VerticalCircularBilliard;
import task3.HorizontalStadiumBilliard;
import util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        HorizontalCircularBilliard horizontalCircularBilliard = new HorizontalCircularBilliard(10);
        List<List<Point>> savedResultsHorizontal= new ArrayList<List<Point>>();

        //10 samples of reflections n, with different starting points
        Stream.generate(horizontalCircularBilliard::start).limit(10).forEach(savedResultsHorizontal::add);

        VerticalCircularBilliard verticalCircularBilliard = new VerticalCircularBilliard(10);
        List<List<Point>> savedResultsVertical= new ArrayList<List<Point>>();

        //10 samples of reflections n, with different starting points
        Stream.generate(verticalCircularBilliard::start).limit(10).forEach(savedResultsVertical::add);

        HorizontalStadiumBilliard horizontalStadiumBilliard = new HorizontalStadiumBilliard(1.5);
        List<List<HorizontalStadiumBilliard.Line>> savedResultsStadium= new ArrayList<List<HorizontalStadiumBilliard.Line>>();

        Stream.generate(horizontalStadiumBilliard::start).limit(10).forEach(savedResultsStadium::add);
    }
}