import static java.lang.System.exit;

/**
 * Circle class represents a circle in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 2.2
 * @inv r > 0
 * @inv P in the first quadrant
 */
public class Circle {
    private final double radius;
    private final Point center;

    /**
     * Constructs a circle with the given radius and center.
     *
     * @param radius the radius of the circle
     * @param center the center of the circle
     */
    public Circle(double radius, Point center) {
        checkInvariant(radius, center);
        this.radius = radius;
        this.center = center;
    }

    /**
     * Checks the invariant of the class.
     *
     * @param radius the radius of the circle
     * @param center the center of the circle
     */
    public static void checkInvariant(double radius, Point center) {
        double Opp = center.getRadius() * Math.sin(Math.toRadians(center.getAngle()));
        double Adj = center.getRadius() * Math.cos(Math.toRadians(center.getAngle()));

        boolean invalidRadius = radius <= 0
                || Math.abs(radius - Math.abs(Opp)) < 1e-9 || radius > Math.abs(Opp)
                || Math.abs(radius - Math.abs(Adj)) < 1e-9 || radius > Math.abs(Adj);

        if (invalidRadius) {
            System.out.println("Circulo:vi");
            exit(0);
        }
    }

    /**
     * Calculate the perimeter of the circle.<br>
     * Formula: 2πr
     *
     * @return The perimeter of the circle
     */
    public double perimeter() {
        return 2 * Math.PI * this.radius;
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

        double APx = this.center.getX() - segment.getA().getX();
        double APy = this.center.getY() - segment.getA().getY();

        double AB_AB = ABx * ABx + ABy * ABy;
        double AP_AB = APx * ABx + APy * ABy;

        double t = Math.clamp(AP_AB / AB_AB, 0, 1);

        Point closest = new Point((int) (segment.getA().getX() + ABx * t), (int) (segment.getA().getY() + ABy * t));

        double distanceToClosest = this.center.distance(closest);

        return Math.abs(distanceToClosest - this.radius) < 1e-9 || distanceToClosest < this.radius;
    }

    /**
     * Returns a string representation of the circle.
     *
     * @return A string representation of the circle
     */
    @Override
    public String toString() {
        return "((" + this.radius + ", " + this.center + "))";
    }
}
