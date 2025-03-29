/**
 * Point class represents a point in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 6.0
 */
public class Point implements Comparable<Point> {
    /**
     * The radius of the point.
     */
    private double x, y;

    /**
     * Constructs a point with the given Cartesian coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the point in the Cartesian plane.
     *
     * @return the x-coordinate of the point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point in the Cartesian plane.
     *
     * @return the y-coordinate of the point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the point in the Cartesian plane.
     *
     * @param x the x-coordinate of the point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the point in the Cartesian plane.
     *
     * @param y the y-coordinate of the point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.<br>
     * sqrt((x2 - x1)^2 + (y2 - y1)^2)
     *
     * @param that the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point that) {
        return Math.sqrt(Math.pow(that.x - x, 2) + Math.pow(that.y - y, 2));
    }

    /**
     * Translates this point by the given dx and dy.
     *
     * @param dx the x-coordinate translation
     * @param dy the y-coordinate translation
     * @return the translated point
     */
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Translates this point by the given point.
     *
     * @param p the point to translate by
     */
    public void translate(Point p) {
        this.x += p.x;
        this.y += p.y;
    }

    /**
     * Rotates this point around the origin.
     *
     * @param angle the angle of rotation
     */
    public void rotate(double angle, Point pivot) {
        double rad = Math.toRadians(angle);
        double newX = pivot.x + (this.x - pivot.x) * Math.cos(rad) - (this.y - pivot.y) * Math.sin(rad);
        double newY = pivot.y + (this.x - pivot.x) * Math.sin(rad) + (this.y - pivot.y) * Math.cos(rad);
        this.x = newX;
        this.y = newY;
    }

    /**
     * Scales this point by the given factor.
     *
     * @param scale the factor to scale by
     */
    public void scale(double scale, Point pivot) {
        this.x = pivot.x + scale * (this.x - pivot.x);
        this.y = pivot.y + scale * (this.y - pivot.y);
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
            return Utils.eq(this.x, that.x) && Utils.eq(this.y, that.y);
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
        return Double.hashCode(this.x) + Double.hashCode(this.y);
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "(%.2f,%.2f)".formatted(x, y);
    }

    /**
     * Compares this point with another point.
     *
     * @param that the other point
     * @return -1 if this point is less than the other point, 0 if they are equal, 1 if this point is greater than the other point
     */
    @Override
    public int compareTo(Point that) {
        return this.x != that.x ? (int) (this.x - that.x) : (int) (this.y - that.y);
    }
}
