import static java.lang.System.exit;

/**
 * Segment class represents a segment between two points.
 *
 * @author Leonardo Albudane
 * @version 1.0
 * @inv a != b
 */
public class Segment {
    public final Point a;
    public final Point b;

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
    }

    /**
     * Checks the invariant of the class.
     *
     * @param a the first point of the segment
     * @param b the second point of the segment
     */
    public static void checkInvariant(Point a, Point b) {
        if (a.equals(b)) {
            System.out.println("Segmento:vi");
            exit(0);
        }
    }

    @Override
    public String toString() {
        return "<" + a + ", " + b + ">";
    }
}
