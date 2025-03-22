import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Polygon class represents a polygon in the plane.
 *
 * @author Leonardo Albudane
 * @version 1.0
 * @inv n >= 3 - The polygon must have more than 3 vertices
 * @inv sideX !intersect sideY - The sides of the polygon cannot intersect
 * @inv 3 !colinear - there can't be more than 2 points in sequence in a line (colinearity)
 */
public class Polygon implements GeometricForm {
    public final Point[] vertices;

    /**
     * Constructor for the Polygon class
     * The Polygon constructor doesn't have any invariants methods, because the complexity of the code is already O(n^2)
     *
     * @param points_string The vertices of the polygon in string format
     */
    public Polygon(String points_string) {
        String[] poly = points_string.split(" ", 2);
        int n = Integer.parseInt(poly[0]);
        Point[] points = Utils.parsePoints(poly[1]);
        if (n < 3) throw new IllegalArgumentException("Poligono:vi");
        Point[] vertices = new Point[points.length];
        Segment[] sides = new Segment[points.length];
        for (int i = 0; i < n; i++) {
            Point p = new Point(points[i]);
            Point q = new Point(points[(i + 1) % points.length]);
            Point r = new Point(points[(i + 2) % points.length]);
            vertices[i] = p;
            Segment seg = new Segment(p, q);
            if (new Line(p, q).has(r)) throw new IllegalArgumentException("Poligono:vi"); // colinearity
            sides[i] = seg;
            for (int j = 0; j < i; j++) {
                if (seg.intersects(sides[j])) throw new IllegalArgumentException("Poligono:vi");
            }
        }
        this.vertices = vertices;
    }

    /**
     * Constructor for the Polygon class
     * The Polygon constructor doesn't have any invariants methods, because the complexity of the code is already O(n^2)
     *
     * @param points The vertices of the polygon
     */
    public Polygon(Point[] points) {
        if (points.length < 3) throw new IllegalArgumentException("Poligono:vi");
        Point[] vertices = new Point[points.length];
        Segment[] sides = new Segment[points.length];
        for (int i = 0; i < points.length; i++) {
            Point p = new Point(points[i]);
            Point q = new Point(points[(i + 1) % points.length]);
            Point r = new Point(points[(i + 2) % points.length]);
            vertices[i] = p;
            Segment seg = new Segment(p, q);
            if (new Line(p, q).has(r)) throw new IllegalArgumentException("Poligono:vi"); // colinearity
            sides[i] = seg;
            for (int j = 0; j < i; j++) {
                if (seg.intersects(sides[j])) throw new IllegalArgumentException("Poligono:vi");
            }
        }
        this.vertices = vertices;
    }

    /**
     * Translates the polygon by dx and dy
     *
     * @param dx The x translation
     * @param dy The y translation
     * @return The translated polygon
     */
    @Override
    public Polygon translate(int dx, int dy) {
        Point[] newVertices = new Point[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            newVertices[i] = new Point(vertices[i].getX() + dx, vertices[i].getY() + dy);
        }
        return new Polygon(newVertices);
    }

    /**
     * Returns the string representation of the polygon
     *
     * @return The string representation of the polygon
     */
    @Override
    public String toString() {
        return "Poligono de %d vertices: ".formatted(vertices.length) + Arrays.toString(vertices);
    }

    /**
     * Verifies if the polygon is equal to another polygon
     * First it verifies if the polygon is null
     * Then it verifies if they are compatible classes
     * Then it verifies if the polygon is the same as the other polygon
     * And finally it verifies if the vertices are the same
     *
     * @param o The other polygon
     * @return True if the polygons are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon poligono)) return false;
        if (vertices.length != poligono.vertices.length) return false;
        HashSet<Point> set = new LinkedHashSet<>(Arrays.asList(vertices));
        set.removeAll(new LinkedHashSet<>(Arrays.asList(poligono.vertices)));
        return set.isEmpty();
    }

    /**
     * Returns the hash code value for the polygon
     *
     * @return The hash code value for the polygon
     */
    @Override
    public int hashCode() {
        Point[] sorted = Arrays.copyOf(vertices, vertices.length);
        Arrays.sort(sorted);
        return Arrays.hashCode(sorted);
    }
}
