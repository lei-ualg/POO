/**
 * Circle class represents a circle in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 4.0
 * @inv r > 0
 * @inv P in the first quadrant
 */
public class Circle extends Rectangle {
    private final double c_radius;
    private final Point c_center;

    /**
     * Constructs a circle with the given radius and center.
     *
     * @param radius the radius of the circle
     * @param center the center of the circle
     */
    public Circle(double radius, Point center) {
        super(checkInvariant(radius, center));
        this.c_radius = radius;
        this.c_center = center;
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
     *
     * @param radius the radius of the circle
     * @param center the center of the circle
     */
    public static String checkInvariant(double radius, Point center) {
        double Opp = center.getRadius() * Math.sin(Math.toRadians(center.getAngle()));
        double Adj = center.getRadius() * Math.cos(Math.toRadians(center.getAngle()));
        boolean invalidRadius = radius <= 0
                || Utils.gt(radius, Math.abs(Opp))
                || Utils.gt(radius, Math.abs(Adj));

        if (invalidRadius) {
            throw new IllegalArgumentException("Circulo:vi");
        }

        return center.getX() + " " + center.getY() + " " + (int) (center.getX() + radius) + " " + center.getY() + " " + (int) (center.getX() + radius) + " " + (int) (center.getY() + radius) + " " + center.getX() + " " + (int) (center.getY() + radius);
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
    public Point getCenter() {
        return c_center;
    }

    /**
     * Calculate the perimeter of the circle.<br>
     * Formula: 2πr
     *
     * @return The perimeter of the circle
     */
    public double perimeter() {
        return 2 * Math.PI * this.c_radius;
    }


    /**
     * Check if the circle intersects with a segment.<br>
     * The circle intersects with the segment if the distance between the center of the circle and the closest point of the segment is less than the radius of the circle.
     *
     * @param segment the segment to check
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
     * Returns a string representation of the circle.
     *
     * @return A string representation of the circle
     */
    @Override
    public String toString() {
        return "Circulo: " + this.getCenter().toString() + " " + (int) c_radius;
    }
}
