/**
 * Point class represents a point in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 1.0
 * @inv none
 */
public class Point {
    private final double radius;
    private final double angle;

    /**
     * Constructs a point with the given radius and angle.
     *
     * @param radius the radius of the point
     * @param angle  the angle of the point
     */
    public Point(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    /**
     * @return the angle of the point.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return the radius of the point.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculates the distance between this point and another point.
     * Formula: sqrt(r1^2 + r2^2 - 2 * r1 * r2 * cos(angle1 - angle2))
     *
     * @param that the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point that) {
        return Math.sqrt(Math.pow(this.radius, 2) + Math.pow(that.radius, 2) - 2 * this.radius * that.radius * Math.cos(Math.toRadians(this.angle - that.angle)));
    }
}
