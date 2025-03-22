/**
 * Point class represents a point in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 5.0
 * @inv 0 ≤ θ ≤ 90 (first quadrant)
 */
public class Point implements  Comparable<Point> {
    /**
     * The radius of the point.
     */
    private final double radius;
    /**
     * The angle of the point.
     */
    private final double angle;

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
     * Constructs a point with the given point.
     *
     * @param p the point to copy
     */
    public Point(Point p) {
        this.radius = p.radius;
        this.angle = p.angle;
    }

    /**
     * Checks the invariant of the class.
     *
     * @param angle the angle of the point
     */
    protected static void checkInvariant(double angle) {
        if (Utils.gt(0, angle) || Utils.gt(angle, 90)) {
            throw new IllegalArgumentException("Ponto:vi");
        }
    }

    /**
     * Returns the radius of the point.
     *
     * @return the radius of the point
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the angle of the point.
     *
     * @return the angle of the point
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Returns the x-coordinate of the point in the Cartesian plane.
     *
     * @return the x-coordinate of the point
     */
    public int getX() {
        return (int) Math.round(this.radius * Math.cos(Math.toRadians(this.angle)));
    }

    /**
     * Returns the y-coordinate of the point in the Cartesian plane.
     *
     * @return the y-coordinate of the point
     */
    public int getY() {
        return (int) Math.round(this.radius * Math.sin(Math.toRadians(this.angle)));
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
     * Translates this point by the given dx and dy.
     *
     * @param dx the x-coordinate translation
     * @param dy the y-coordinate translation
     * @return the translated point
     */
    public Point translate(int dx, int dy) {
        return new Point(this.getX() + dx, this.getY() + dy);
    }

    /**
     * Compares this point to the specified object.
     *
     * @param obj the object to compare
     * @return true if the points are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point that) {
            return Utils.eq(this.radius, that.radius) && Utils.eq(this.angle, that.angle);
        }
        return false;
    }

    /**
     * Returns a hash code value for the point.
     *
     * @return a hash code value for the point
     */
    @Override
    public int hashCode() {
        return Double.hashCode(this.radius) + Double.hashCode(this.angle);
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    /**
     * Compares this point with another point.
     *
     * @param that the other point
     * @return -1 if this point is less than the other point, 0 if they are equal, 1 if this point is greater than the other point
     */
    @Override
    public int compareTo(Point that) {
        if (this.radius < that.radius) return -1;
        if (this.radius > that.radius) return 1;
        return Double.compare(this.angle, that.angle);
    }
}
