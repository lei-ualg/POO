import static java.lang.System.exit;

/**
 * Point class represents a point in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 2.0
 * @inv 0 ≤ θ ≤ 90 (first quadrant)
 * @inv |r| &lt; 10 (distance from the origin)
 */
public class Point {
    /**
     * The radius of the point.
     */
    private final double radius;
    /**
     * The angle of the point.
     */
    private final double angle;

    /**
     * Constructs a point with the given radius and angle.
     *
     * @param radius the radius of the point
     * @param angle  the angle of the point
     * @inv 0 ≤ θ ≤ 90 (first quadrant)
     * @inv |r| &lt; 10 (distance from the origin)
     */
    public Point(double radius, double angle) {
        checkInvariant(radius, angle);
        this.radius = radius;
        this.angle = angle;
    }

    /**
     * Checks the invariant of the class.
     *
     * @param radius the angle of the point
     * @param angle  the angle of the point
     */
    protected static void checkInvariant(double radius, double angle) {
        if (Math.abs(radius) > 10 || angle < 0 || angle > 90) {
            System.out.print("iv");
            exit(0);
        }
    }

    /**
     * Gets the angle of the point.
     *
     * @return the angle of the point.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Gets the radius of the point.
     *
     * @return the radius of the point.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculates the distance between this point and another point.<br>
     * Formula: √( r<sub>1</sub><sup>2</sup> + r<sub>2</sub><sup>2</sup> - 2 × r<sub>1</sub> × r<sub>2</sub> × cos(θ<sub>1</sub> - θ<sub>2</sub>) )
     *
     * @param that the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point that) {
        return Math.sqrt(Math.pow(this.getRadius(), 2) + Math.pow(that.getRadius(), 2) - 2 * this.getRadius() * that.radius * Math.cos(Math.toRadians(this.getAngle() - that.getAngle())));
    }
}
