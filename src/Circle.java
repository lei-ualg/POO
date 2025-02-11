import static java.lang.System.exit;

/**
 * Circle class represents a circle in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 2.0
 * @inv r > 0
 * @inv P in the first quadrant
 */
public class Circle {
    public final double radius;
    public final Point center;

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
        float Opp = (float) (center.radius * Math.sin(Math.toRadians(center.angle)));
        float Adj = (float) (center.radius * Math.cos(Math.toRadians(center.angle)));
        if (radius <= 0 || radius > Math.abs(Opp) || radius > Math.abs(Adj)) {
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
     */
    public boolean intersects(Segment segment) {
        float ABx = (float) (segment.b.getX() - segment.a.getX());
        float ABy = (float) (segment.b.getY() - segment.a.getY());

        float APx = (float) (this.center.getX() - segment.a.getX());
        float APy = (float) (this.center.getY() - segment.a.getY());

        float AB_AB = ABx * ABx + ABy * ABy;
        float AP_AB = APx * ABx + APy * ABy;

        float t = Math.clamp(AP_AB / AB_AB, 0, 1);

        Point closest = new Point((int) (segment.a.getX() + ABx * t), (int) (segment.a.getY() + ABy * t));
        return this.center.distance(closest) < this.radius;
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
