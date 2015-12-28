// Copyright (c) 2014 Mark A. Gallagher Jr (mag0038@auburn.edu).

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class LineTest {

    @Test
    public void testConstructor() {
        // intended use: all points are unique, collinear, and listed in
        // obvious "line order"
        Point[] points = {new Point(0,0), new Point(1,3), new Point(2,6), new Point(3,9)};
        Line line = new Line(Arrays.asList(points));
        System.out.println(line);

        // possible uses: points are not all unqiue, not all collinear,
        // and listed in no particular order
        // NOTE: the collection's iterator order will determine
        // the specific line that will be created from the given points

        // arrangement 1
        points = new Point[]{new Point(4,6), new Point(5,3), new Point(6,0),
                new Point(3,3), new Point(3,9), new Point(6,6), new Point(7,9),
                new Point(5,3)};
        line = new Line(Arrays.asList(points));
        System.out.println(line);

        // arrangement 2
        points = new Point[]{new Point(6,6), new Point(7,9), new Point(4,6),
                new Point(3,4), new Point(5,3), new Point(6,0), new Point(3,9),
                new Point(5,3)};
        line = new Line(Arrays.asList(points));
        System.out.println(line);

        // arrangement 3
        Arrays.sort(points);
        line = new Line(Arrays.asList(points));
        System.out.println(line);
    }

    @Test
    public void testAdd() {
        Line line = new Line();
        line.add(new Point(0,1));
        line.add(new Point(0,2));
        line.add(new Point(2,0));
        System.out.println(line);
    }

    @Test
    public void testFirst() {

    }

    @Test
    public void testLast() {

    }

    @Test
    public void testLength() {

    }

    @Test
    public void testCompareTo() {

    }
}