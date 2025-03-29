/**
 * Circle class represents a circle in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 6.0
 * @inv r > 0
 */
public class Circle extends GeometricForm {
    private double c_radius;
    private final Point c_center;

    /**
     * Constructs a circle with the given radius and center.
     *
     * @param radius the radius of the circle
     * @param center the center of the circle
     */
    public Circle(double radius, Point center) {
        checkInvariant(radius);
        this.c_center = center;
        this.c_radius = radius;
    }

    /**
     * Constructs a circle with the given string.
     *
     * @param circle_string the string to be parsed
     */
    public Circle(String circle_string) {
        this(Double.parseDouble(circle_string.split(" ")[2]), Utils.parsePoints(circle_string)[0]);
    }

    /**
     * Checks the invariant of the class.
     * Verifies that:
     * 1. The radius is positive
     *
     * @param radius the radius of the circle
     * @throws IllegalArgumentException if any invariant is violated
     */
    public static void checkInvariant(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Circulo:vi");
        }
    }

    /**
     * Returns the radius of the circle.
     *
     * @return the radius of the circle
     */
    public double getCircleRadius() {
        return c_radius;
    }

    /**
     * Returns the center of the circle.
     *
     * @return the center of the circle
     */
    public Point getCircleCenter() {
        return c_center;
    }

    /**
     * Translates the circle by dx and dy.
     *
     * @param dx the x-coordinate translation
     * @param dy the y-coordinate translation
     * @return the translated circle
     */
    @Override
    public void translate(double dx, double dy) {
        this.c_center.translate(dx, dy);
    }

    /**
     * Calculate the perimeter of the circle.
     * Uses the standard formula for circle perimeter: 2πr where r is the radius.
     *
     * @return The perimeter of the circle as a double value
     */
    public double perimeter() {
        return 2 * Math.PI * this.c_radius;
    }


    /**
     * Checks if the circle intersects with a segment.
     * Uses the following algorithm:
     * 1. Find the closest point on the segment to the center of the circle
     * 2. Calculate the projection of vector AP onto vector AB
     * 3. Clamp the projection to ensure the closest point is on the segment
     * 4. Calculate the coordinates of the closest point
     * 5. Check if the distance from the circle center to this point is less than or equal to radius
     *
     * @param segment the segment to check for intersection
     * @return true if the circle intersects with the segment, false otherwise
     * @see <a href="https://stackoverflow.com/questions/47481774/getting-point-on-line-segment-that-is-closest-to-another-point">Getting point on a line segment that is closest to another point [SO]</a>
     */
    public boolean intersects(Segment segment) {
        double ABx = segment.getB().getX() - segment.getA().getX();
        double ABy = segment.getB().getY() - segment.getA().getY();

        double APx = this.c_center.getX() - segment.getA().getX();
        double APy = this.c_center.getY() - segment.getA().getY();

        double AB_AB = ABx * ABx + ABy * ABy;
        double AP_AB = APx * ABx + APy * ABy;

        double t = Math.clamp(AP_AB / AB_AB, 0, 1);

        Point closest = new Point((int) (segment.getA().getX() + ABx * t), (int) (segment.getA().getY() + ABy * t));

        return Utils.ge(this.c_radius, this.c_center.distance(closest));
    }

    /**
     * Checks if the circle intersects with another geometric form.
     * The method handles different types of geometric forms:
     * - For circles: Checks if the distance between centers is less than or equal to the sum of radii
     * - For other forms: Delegates to the other form's intersection detection
     *
     * @param p The other geometric form to check intersection with
     * @return True if the circle intersects with the other geometric form, false otherwise
     */
    @Override
    public boolean intersects(Polygon p) {
        return p.intersects(this);
    }

    @Override
    public boolean intersects(Circle c) {
        return !Utils.gt(this.c_center.distance(c.c_center), this.c_radius + c.c_radius);
    }


    /**
     * Calculates the area of the circle.
     * Uses the standard formula for circle area: πr² where r is the radius.
     *
     * @return The area of the circle as a double value
     */
    @Override
    public double getArea() {
        return Math.PI * Math.pow(this.c_radius, 2);
    }

    /**
     * Returns the bounding box of the circle.
     *
     * @return the bounding box of the circle
     */
    @Override
    public Polygon getBoundingBox() {
        return new Polygon(new Point((int) (this.c_center.getX() - this.c_radius), (int) (this.c_center.getY() + this.c_radius)),
                new Point((int) (this.c_center.getX() + this.c_radius), (int) (this.c_center.getY() - this.c_radius)));
    }

    /**
     * Returns the centroid of the circle.
     * @return the centroid of the circle
     */
    @Override
    public Point getCentroid() {
        return this.c_center;
    }

    /**
     * Sets the centroid of the circle.
     * @param p the new centroid
     */
    @Override
    public void setCentroid(Point p) {
        this.c_center.setX(p.getX());
        this.c_center.setY(p.getY());
    }

    /**
     * Scales the circle by a factor.
     * @param dScale the factor to scale the circle by
     */
    @Override
    public void scale(double dScale) {
        this.c_radius *= dScale;
    }

    /**
     * Does nothing since circles are rotation invariant.
     */
    @Override
    public void rotate(double dScale) {
        // Do nothing
    }

    /**
     * Checks if a point is inside the circle.
     *
     * @param point the point to check
     * @return true if the point is inside the circle, false otherwise
     */
    @Override
    public boolean isPointInside(Point point) {
        return Utils.gt(this.c_radius, this.c_center.distance(point));
    }

    /**
     * Returns a string representation of the circle.
     *
     * @return A string representation of the circle
     */
    @Override
    public String toString() {
        return "%s %.2f".formatted(this.c_center.toString(), this.c_radius);
    }
}
