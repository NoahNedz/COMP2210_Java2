import java.util.Comparator;

/**
 * Point.java. Models a two dimensional point in terms
 * of (x, y) coordinates.
 *
 * @author   Mark Gallagher (mmag0038@tigermail.auburn.edu)
 * @author   Dean Hendrix (dh@auburn.edu)
 * @version  2014-09-23
 *
 */
public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator(); // change this, of course

    // x,y coordinates
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        // If you choose to use this, simply uncomment the following line
        // and add stdlib.jar to your CLASSPATH.
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        // If you choose to use this, simply uncomment the following line
        // and add stdlib.jar to your CLASSPATH.
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        int yDiff = that.y - this.y;
        int xDiff = that.x - this.x;

        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY; // Slope of degenerate line segment
        if (xDiff == 0) return Double.POSITIVE_INFINITY; // Slope of vertical line segment
        if (yDiff == 0) return 0.0; // Slope of horizontal line segment

        return (double)yDiff / xDiff;
    }

    // Does this point precede that point in natural order?
    // comparing y-coordinates and breaking ties by x-coordinates
    @Override
    public int compareTo(Point that) {
        int result = this.y - that.y;

        if (result == 0) {
            result = this.x - that.x;
        }

        return result;
    }

    @Override
    public String toString() {
        // DO NOT MODIFY
        return "(" + x + ", " + y + ")";
    }

    public class SlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(slopeTo(p1), slopeTo(p2));
        }
    }

}