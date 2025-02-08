import static java.lang.System.exit;

/**
 * Path class represents a path of points in polar coordinates.
 * The path is represented as an array of points.
 * The distance of the path is calculated by summing the distances between each pair of points.
 *
 * @author Leonardo Albudane
 * @version 1.0
 * @inv Path n ≥ 2
 */
public class Path {
    private final Point[] points;

    /**
     * Default constructor, initializes the array of points with the given size
     *
     * @param n The number of points in the path
     */
    public Path(int n) {
        checkInvariant(n);
        points = new Point[n];
    }

    /**
     * Checks the invariant of the class.
     *
     * @param n the number of points in the path
     */
    protected static void checkInvariant(int n) {
        if (n < 2) {
            System.out.print("iv");
            exit(0);
        }
    }

    /**
     * Add a point to the array of points
     *
     * @param i     The index of the point
     * @param r     The radius of the point
     * @param angle The angle of the point
     */
    public void addToPath(int i, double r, double angle) {
        this.points[i] = new Point(r, angle);
    }

    /**
     * Calculate the path distance by summing the distances between each pair of points
     *
     * @return The path distance
     */
    public double distance() {
        double dist = 0;
        for (int i = 0; i < this.points.length - 1; i++) {
            dist += this.points[i].distance(this.points[i + 1]);
        }
        return dist;
    }
}
