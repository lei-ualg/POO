import java.util.Arrays;

/**
 * Rectangle class represents a rectangle in the plane.
 *
 * @author Leonardo Albudane
 * @version 4.0
 * @inv d1 = d2
 */
public class Rectangle extends Polygon {

    /**
     * Constructs a rectangle with the given points.
     *
     * @param points_string the list of points that form the rectangle in string format
     */
    public Rectangle(String points_string) {
        super(checkInvariant(points_string));
    }

    /**
     * Constructs a rectangle with the given points.
     * This constructor will assume that the array of points is valid.
     *
     * @param vertices the list of points that form the rectangle
     */
    private Rectangle(Point[] vertices) {
        super(vertices);
    }

    /**
     * Constructs a rectangle with the given points.
     * Using the top left and bottom right points of the rectangle.
     *
     * @param topLeft   the top left point of the rectangle
     * @param bottomRig the bottom right point of the rectangle
     */
    public Rectangle(Point topLeft, Point bottomRig) {
        super(new Point[]{
                topLeft,
                new Point(topLeft.getX(), bottomRig.getY()),
                bottomRig,
                new Point(bottomRig.getX(), topLeft.getY())
        });
    }

    /**
     * Checks the invariant of the class.
     *
     * @param points_string the list of points that form the rectangle in string format
     */
    public static Point[] checkInvariant(String points_string) {
        Point[] points = Utils.parsePoints(points_string);
        double d1 = points[0].distance(points[2]);
        double d2 = points[1].distance(points[3]);
        if (!Utils.eq(d1, d2)) {
            throw new IllegalArgumentException("Retangulo:vi");
        }
        return points;
    }

    @Override
    public Rectangle translate(int dx, int dy) {
        return new Rectangle(translatePoints(vertices, dx, dy));
    }

    /**
     * Returns the list of points that form the rectangle.
     *
     * @return the list of points that form the rectangle
     */
    @Override
    public String toString() {
        return "Retangulo: " + Arrays.toString(vertices);
    }
}
