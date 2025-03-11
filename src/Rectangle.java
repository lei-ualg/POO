import java.util.Arrays;

/**
 * Rectangle class represents a rectangle in the plane.
 *
 * @author Leonardo Albudane
 * @version 3.0
 * @inv d1 = d2
 */
public class Rectangle extends Polygon {

    /**
     * Constructs a rectangle with the given points.
     *
     * @param points_string the list of points that form the rectangle in string format
     */
    public Rectangle(String points_string) {
        super(checkInvariant(points_string));
    }

    /**
     * Checks the invariant of the class.
     *
     * @param points_string the list of points that form the rectangle in string format
     */
    public static Point[] checkInvariant(String points_string) {
        Point[] points = Utils.parsePoints(points_string);
        double d1 = points[0].distance(points[2]);
        double d2 = points[1].distance(points[3]);
        if (!Utils.eq(d1, d2)) {
            throw new IllegalArgumentException("Retangulo:vi");
        }
        return points;
    }

    /**
     * Checks if a segment intersects the rectangle.
     *
     * @param seg the segment to be analyzed
     * @return true if the segment intersects the rectangle, false otherwise
     */
    public boolean intersects(Segment seg) {
        int n = 4;
        while (n-- > 0) {
            Segment side = new Segment(vertices[n], vertices[(n + 1) % 4]);
            if (seg.intersects(side)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the list of points that form the rectangle.
     *
     * @return the list of points that form the rectangle
     */
    @Override
    public String toString() {
        return "Retangulo: " + Arrays.toString(vertices);
    }
}
