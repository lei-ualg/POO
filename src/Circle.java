import static java.lang.System.exit;

/**
 * Circle class represents a circle in polar coordinates.
 *
 * @author Leonardo Albudane
 * @version 1.0
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
     * Returns a string representation of the circle.
     *
     * @return A string representation of the circle
     */
    @Override
    public String toString() {
        return "((" + this.radius + ", " + this.center + "))";
    }
}
