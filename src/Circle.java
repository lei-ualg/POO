/**
 * Circle class represents a circle in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 5.0
 * @inv r > 0
 * @inv P in the first quadrant
 */
public class Circle extends GeometricForm {
    private final double c_radius;
    private final Point c_center;

    /**
     * Constructs a circle with the given radius and center.
     *
     * @param radius the radius of the circle
     * @param center the center of the circle
     */
    public Circle(double radius, Point center) {
        this.c_center = checkInvariant(radius, center);
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
     * 2. The center point is valid (the circle must be fully in the first quadrant)
     *
     * @param radius the radius of the circle
     * @param center the center of the circle
     * @return the validated center point
     * @throws IllegalArgumentException if any invariant is violated
     */
    public static Point checkInvariant(double radius, Point center) {
        double Opp = center.getRadius() * Math.sin(Math.toRadians(center.getAngle()));
        double Adj = center.getRadius() * Math.cos(Math.toRadians(center.getAngle()));
        boolean invalidRadius = radius <= 0
                || Utils.gt(radius, Math.abs(Opp))
                || Utils.gt(radius, Math.abs(Adj));

        if (invalidRadius) {
            throw new IllegalArgumentException("Circulo:vi");
        }
        return center;
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
    public Circle translate(int dx, int dy) {
        return new Circle(this.c_radius, this.c_center.translate(dx, dy));
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
     * - For other forms: Delegates to the other form's collision detection
     *
     * @param f The other geometric form to check intersection with
     * @return True if the circle intersects with the other geometric form, false otherwise
     */
    @Override
    public boolean intersects(GeometricForm f) {
        if (f instanceof Circle c) {
            return !Utils.gt(this.c_center.distance(c.c_center), this.c_radius + c.c_radius);
        } else {
            return f.collides(this);
        }
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
     * @return the bounding box of the circle
     */
    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(new Point((int) (this.c_center.getX() - this.c_radius), (int) (this.c_center.getY() + this.c_radius)),
                new Point((int) (this.c_center.getX() + this.c_radius), (int) (this.c_center.getY() - this.c_radius)));
    }

    /**
     * Checks if a point is inside the circle.
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
        return "Circulo: " + c_center.toString() + " " + (int) c_radius;
    }
}
