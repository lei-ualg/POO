/**
 * A class to represent a line in the plane
 *
 * @author Leonardo Albudane
 * @version 1.0
 * @inv p1 != p2 - The points are different
 */
public class Line {
    /**
     * The coefficients of the line
     */
    private final int a, b, c;

    /**
     * Constructor for the Line class
     *
     * @param p1 The first point
     * @param p2 The second point
     * @see <a href="https://www.geeksforgeeks.org/program-find-line-passing-2-points/">Find line from 2 points</a>
     */
    public Line(Point p1, Point p2) {
        checkInvariant(p1, p2);
        this.a = p2.getY() - p1.getY();
        this.b = p1.getX() - p2.getX();
        this.c = (p2.getX() * p1.getY()) - (p1.getX() * p2.getY());
    }

    /**
     * Checks the invariant of the class
     *
     * @param p1 The first point
     * @param p2 The second point
     */
    private static void checkInvariant(Point p1, Point p2) {
        if (p1.equals(p2)) {
            throw new IllegalArgumentException("Line:vi");
        }
    }

    /**
     * Verifies where a point is in relation to the line
     *
     * @param p The point to be analyzed
     * @return Where the point is located in relation to the line
     */
    public int at(Point p) {
        return a * p.getX() + b * p.getY() + c;
    }

    /**
     * Verifies if the point satisfies the equation of the line
     *
     * @param p The point to be verified
     * @return true if the point satisfies the equation of the line, false otherwise
     */
    public boolean has(Point p) {
        return at(p) == 0;
    }

    /**
     * Verifies if two points are in different sides of the line
     *
     * @param p1 The first point
     * @param p2 The second point
     * @return true if the points are in different sides of the line, false otherwise
     */
    public boolean opposedSides(Point p1, Point p2) {
        return at(p1) * at(p2) < 0;
    }

    @Override
    public String toString() {
        return "(" + a + "x + " + b + "y + " + c + " = 0)";
    }
}
