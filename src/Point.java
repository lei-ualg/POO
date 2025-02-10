import static java.lang.System.exit;

/**
 * Point class represents a point in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 3.0
 * @inv 0 ≤ θ ≤ 90 (first quadrant)
 */
public class Point {
    /**
     * The radius of the point.
     */
    public final double radius;
    /**
     * The angle of the point.
     */
    public final double angle;

    /**
     * Constructs a point with the given polar coordinates.
     *
     * @param radius the radius of the point
     * @param angle  the angle of the point
     */
    public Point(double radius, double angle) {
        checkInvariant(angle);
        this.radius = radius;
        this.angle = angle;
    }

    /**
     * Constructs a point with the given Cartesian coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(int x, int y) {
        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double angle = Math.toDegrees(Math.atan2(y, x));
        checkInvariant(angle);
        this.radius = radius;
        this.angle = angle;
    }

    /**
     * Checks the invariant of the class.
     *
     * @param angle the angle of the point
     */
    protected static void checkInvariant(double angle) {
        if (angle < 0 || angle > 90) {
            System.out.println("Ponto:vi");
            exit(0);
        }
    }

    /**
     * Calculates the distance between this point and another point.<br>
     * Formula: √( r<sub>1</sub><sup>2</sup> + r<sub>2</sub><sup>2</sup> - 2 × r<sub>1</sub> × r<sub>2</sub> × cos(θ<sub>1</sub> - θ<sub>2</sub>) )
     *
     * @param that the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point that) {
        return Math.sqrt(Math.pow(this.radius, 2) + Math.pow(that.radius, 2) - 2 * this.radius * that.radius * Math.cos(Math.toRadians(this.angle - that.angle)));
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "(" + this.radius + ", " + this.angle + ")";
    }
}
