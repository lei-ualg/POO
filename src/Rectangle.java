import java.util.List;

/**
 * Rectangle class represents a rectangle in the plane.
 *
 * @author Leonardo Albudane
 * @version 2.0
 * @inv d1 = d2
 */
public class Rectangle {
    /**
     * The list of points that form the rectangle.
     */
    private final List<Point> points;

    /**
     * Constructs a rectangle with the given points.
     *
     * @param points the list of points that form the rectangle
     */
    Rectangle(List<Point> points) {
        checkInvariant(points);
        this.points = points;
    }

    /**
     * Checks the invariant of the class.
     *
     * @param points the list of points that form the rectangle
     */
    public static void checkInvariant(List<Point> points) {
        double d1 = points.get(0).distance(points.get(2));
        double d2 = points.get(1).distance(points.get(3));
        if (!Utils.eq(d1, d2)) {
            throw new IllegalArgumentException("Retangulo:vi");
        }
    }

    /**
     * Checks if a segment intersects the rectangle.
     *
     * @param seg the segment to be analyzed
     * @return true if the segment intersects the rectangle, false otherwise
     */
    public boolean intersects(Segment seg) {
        int n = 4;
        while (n-->0) {
            Segment side = new Segment(points.get(n), points.get((n + 1) % 4));
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
        return points.toString();
    }
}
