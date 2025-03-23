/**
 * Utility class for the project.
 * Epsilon is set to 1e-9.
 *
 * @author Leonardo Albudane
 * @version 2.0
 */
public final class Utils {
    /**
     * The epsilon value for the comparison of double values.
     */
    public static final double epsilon = 1e-9;

    /**
     * Checks if two double values are equal.
     *
     * @param d the first double value
     * @param g the second double value
     * @return true if the values are equal based on the epsilon, false otherwise
     */
    public static boolean eq(double d, double g) {
        return Math.abs(d - g) < epsilon;
    }

    /**
     * Checks if the first double value is greater than the second double value.
     *
     * @param d the first double value
     * @param g the second double value
     * @return true if the first value is greater than the second value, false otherwise
     */
    public static boolean gt(double d, double g) {
        return d - g > epsilon;
    }

    /**
     * Checks if the first double value is greater than or equal to the second double value.
     *
     * @param d the first double value
     * @param g the second double value
     * @return true if the first value is greater than or equal to the second value, false otherwise
     */
    public static boolean ge(double d, double g) {
        return eq(d, g) || gt(d, g);
    }

    /**
     * Parses a string to an Array of Points.
     *
     * @param str the string to be parsed
     * @return the array of points
     */
    public static Point[] parsePoints(String str) {
        String[] points_string = str.split(" ");
        int n = points_string.length / 2;
        Point[] vertices = new Point[n];
        for (int i = 0, a = 0, b = 1; i < n; i++, a += 2, b += 2) {
            int x = Integer.parseInt(points_string[a]);
            int y = Integer.parseInt(points_string[b]);
            vertices[i] = new Point(x, y);
        }
        return vertices;
    }

    /**
     * Calculates the bounding box that encompasses two rectangles.
     *
     * @param r1 the first rectangle
     * @param r2 the second rectangle
     * @return a new rectangle representing the smallest bounding box that contains both input rectangles
     */
    public static Rectangle sumBoundingBox(Rectangle r1, Rectangle r2) {
        Point topLeft1 = r1.vertices[0];
        Point bottomRig1 = r1.vertices[2];
        Point topLeft2 = r2.vertices[0];
        Point bottomRig2 = r2.vertices[2];
        int x1 = Math.min(topLeft1.getX(), topLeft2.getX());
        int y1 = Math.max(topLeft1.getY(), topLeft2.getY());
        int x2 = Math.max(bottomRig1.getX(), bottomRig2.getX());
        int y2 = Math.min(bottomRig1.getY(), bottomRig2.getY());
        return new Rectangle(new Point(x1, y1), new Point(x2, y2));
    }
}
