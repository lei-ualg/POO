import java.util.Arrays;

/**
 * Triangle class represents a triangle in the plane.
 *
 * @author Leonardo Albudane
 * @version 1.0
 * @inv n = 3 - The triangle must have 3 vertices
 */
public class Triangle extends Polygon {

    /**
     * Constructor for the Triangle class using an array of points
     * This constructor will assume that the array of points is valid
     *
     * @param points_string The vertices of the triangle in string format
     */
    public Triangle(String points_string) {
        super(checkInvariant(points_string));
    }


    /**
     * Checks the invariant of the class.
     *
     * @param points_string the list of points that form the triangle
     */
    public static Point[] checkInvariant(String points_string) {
        Point[] points = Utils.parsePoints(points_string);
        if (points.length != 3) {
            throw new IllegalArgumentException("Triangulo:vi");
        }
        return points;
    }


    /**
     * Returns the list of points that form the rectangle.
     *
     * @return the list of points that form the rectangle
     */
    @Override
    public String toString() {
        return "Triangulo: " + Arrays.toString(vertices);
    }
}
