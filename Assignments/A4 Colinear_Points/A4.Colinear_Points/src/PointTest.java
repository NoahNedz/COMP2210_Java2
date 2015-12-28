// Copyright (c) 2014 Mark A. Gallagher Jr (mag0038@auburn.edu).

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void testSlopeTo() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(5, 2);
        Point p5 = new Point(7, 3);
        Point p6 = new Point(3, 0);
        Point p7 = new Point(3, 3);

        double expected = 1.0;
        double actual = p1.slopeTo(p2);
        Assert.assertEquals("SlopeTo FAILED", expected, actual, 0.0001);

        expected = 2.0;
        actual = p1.slopeTo(p3);
        Assert.assertEquals("SlopeTo FAILED", expected, actual, 0.0001);

        expected = -0.5;
        actual = p1.slopeTo(p4);
        Assert.assertEquals("SlopeTo FAILED", expected, actual, 0.0001);

        expected = 0.0;
        actual = p1.slopeTo(p5);
        Assert.assertEquals("SlopeTo FAILED", expected, actual, 0.0001);

        expected = Double.POSITIVE_INFINITY;
        actual = p1.slopeTo(p6);
        Assert.assertEquals("SlopeTo FAILED", expected, actual, 0.0001);

        expected = Double.NEGATIVE_INFINITY;
        actual = p1.slopeTo(p7);
        Assert.assertEquals("SlopeTo FAILED", expected, actual, 0.0001);
    }

    @Test
    public void testCompareTo() {
        Point p1 = new Point(0, 1);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(7, 1);
        Point p4 = new Point(5, 3);
        Point p5 = new Point(3, 0);
        Point p6 = new Point(4, 0);

        int expected = -1;
        int actual = p1.compareTo(p2);
        Assert.assertEquals("CompareTo FAILED", expected, actual);

        expected = -2;
        actual = p3.compareTo(p4);
        Assert.assertEquals("CompareTo FAILED", expected, actual);

        expected = -1;
        actual = p5.compareTo(p6);
        Assert.assertEquals("CompareTo FAILED", expected, actual);
    }

    @Test
    public void testCompare() {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(5, 2);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 5);

        int expected = -1;
        int actual = p1.SLOPE_ORDER.compare(p2, p3);
        Assert.assertEquals("Compare FAIL", expected, actual);

        expected = -1;
        actual = p1.SLOPE_ORDER.compare(p3, p4);
        Assert.assertEquals("Compare FAIL", expected, actual);

        expected = 2147483647; // Infinity in integer
        actual = p1.SLOPE_ORDER.compare(p5, p2);
        Assert.assertEquals("Compare FAIL", expected, actual);
    }
}