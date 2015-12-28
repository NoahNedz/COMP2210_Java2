import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * The import statements offer a strong suggestion of what set
 * implementation to use: java.util.TreeSet. This isn't required,
 * but it's a good choice.
 *
 * @author  Mark Gallagher (mag0038@tigermail.auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2014-09-23
 *
 */
public class Extractor {

    // resolution settings for stdlib drawing
    private static int HI_RES = 32768;
    private static int LO_RES = 32;

    private TreeSet<Point> points = new TreeSet<Point>();
    private TreeSet<Line> lines = new TreeSet<Line>();

    // Build an extractor based on the points in the
    // file named by filename.
    public Extractor(String filename) throws IOException {
        int cX, cY; // Coordinates x and y

        Scanner fileScan = new Scanner(new File(filename));
        if (!fileScan.hasNextLine()) throw new IOException("Empty File!");
        Scanner lineScan = new Scanner(fileScan.nextLine());
        int numOfLines = lineScan.nextInt();

        for (int i = 0; i < numOfLines; i++) {
            lineScan = new Scanner(fileScan.nextLine());
            while (!lineScan.hasNextInt()) { // Check against empty lines between Point data
                lineScan = new Scanner(fileScan.nextLine());
            }
            cX = lineScan.nextInt();
            cY = lineScan.nextInt();
            points.add(new Point(cX, cY));
        }

    }

    // Build an extractor based on the points in the
    // Collection named by c
    public Extractor(Collection<Point> c) {
        points = new TreeSet<Point>(c);
    }

    // Identify all 4-point collinear line segments
    // using a brute force combinatoric approach.
    public Set<Line> getLinesBrute() {
        lines = new TreeSet<Line>();
        // Have check to ensure "points" is longer than four elements!
        if (points.size() < 4) return null;

        for (Point p1 : points) {
            for (Point p2 : points) {
                for (Point p3 : points) {
                    for (Point p4: points) {
                        if (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4)) {
                            TreeSet<Point> temp = new TreeSet<Point>();
                            temp.add(p1);
                            temp.add(p2);
                            temp.add(p3);
                            temp.add(p4);
                            if (temp.size() == 4) {
                                lines.add(new Line(temp));
                            }
                        }
                    }
                }
            }
        }

        return lines;
    }

    // Identify all maximal line segments of four or
    // more collinear points using a sort and scan approach.
    public Set<Line> getLinesFast() {
        Iterator<Point> itr = points.iterator();
        Point[] sortedPoints = new Point[points.size()];
        int i = 0;
        for (Point pointCopy : points) {
            sortedPoints[i] = pointCopy;
            i++;
        }

        for (Point p : points) {
            Arrays.sort(sortedPoints, p.SLOPE_ORDER);
            Line temp = new Line();
            Double prevSlope = p.slopeTo(p);
            for (Point element : sortedPoints) {
                if (p.slopeTo(element) != prevSlope) {
                    if (temp.length() >= 4) lines.add(temp);
                    temp = new Line();
                    temp.add(p);
                }
                temp.add(element);
                prevSlope = p.slopeTo(element);
            }
            if (temp.length() >= 4) lines.add(temp);
        }

        return lines;
    }

    // Draw all points to a graphics window.
    // This method assumes that the stdlib.jar file is on your
    // CLASSPATH.
    // This method also assumes that all the points you want to draw
    // are in an Iterable container of Points (points below).
    public void drawPoints() {
        initDrawing();
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show(0);
    }

    // Draw all identified lines, if any, to a graphics window.
    // This method assumes that the stdlib.jar file is on your
    // CLASSPATH.
    // This method also assumes that all the lines you want to draw
    // are in an Iterable container of Lines (lines below).
    public void drawLines() {
        initDrawing();
        for (Line line : lines) {
            line.first().drawTo(line.last());
        }
        StdDraw.show(0);
    }

    // initializes the drawing canvas for StdDraw.
    // If you decide to use the two public 'draw' methods
    // above, uncomment the following method.
    // Note that you can change the resolution for
    // large and small data sets.
    private void initDrawing() {
       // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, HI_RES);
        StdDraw.setYscale(0, HI_RES);
        StdDraw.show(0);
    }


}