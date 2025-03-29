/**
 * Segment class represents a segment between two points.
 *
 * @author Leonardo Albudane
 * @version 3.1
 * @inv a != b
 */
public class Segment {
    private final Point a;
    private final Point b;
    public final double slope;

    /**
     * Constructs a segment with the given points.
     *
     * @param a the first point of the segment
     * @param b the second point of the segment
     */
    public Segment(Point a, Point b) {
        checkInvariant(a, b);
        this.a = a;
        this.b = b;
        this.slope = (b.getY() - a.getY()) / (b.getX() - a.getX());
    }

    /**
     * Checks the invariant of the class.
     *
     * @param a the first point of the segment
     * @param b the second point of the segment
     */
    public static void checkInvariant(Point a, Point b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Segmento:vi");
        }
    }

    /**
     * Returns the first point of the segment.
     *
     * @return the first point of the segment
     */
    public Point getA() {
        return a;
    }

    /**
     * Returns the second point of the segment.
     *
     * @return the second point of the segment
     */
    public Point getB() {
        return b;
    }

    /**
     * Verifies if two segments intersect.
     *
     * @param seg the segment to be analyzed
     * @return true if the segments intersect, false otherwise
     */
    public boolean intersects(Segment seg) {
        Line line1 = new Line(a, b);
        Line line2 = new Line(seg.getA(), seg.getB());
        return line1.opposedSides(seg.getA(), seg.getB()) && line2.opposedSides(a, b);
    }

    @Override
    public String toString() {
        return "<" + a + ", " + b + ">";
    }
}
