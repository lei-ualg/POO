import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Polygon class represents a polygon in the plane.
 *
 * @author Leonardo Albudane
 * @version 3.0
 * @inv n >= 3 - The polygon must have more than 3 vertices
 * @inv sideX !intersect sideY - The sides of the polygon cannot intersect
 * @inv 3 !colinear - there can't be more than 2 points in sequence in a line (colinearity)
 */
public class Polygon extends GeometricForm {
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
     * Checks if this polygon intersects with another geometric form
     *
     * @param other The other geometric form to check intersection with
     * @return True if the polygon intersects with the other geometric form, false otherwise
     */
    @Override
    public boolean intersects(GeometricForm other) {
        if (other instanceof Polygon p) {
            for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
                Segment s = new Segment(vertices[i], vertices[j]);
                for (int k = 0, l = p.vertices.length - 1; k < p.vertices.length; l = k++) {
                    Segment t = new Segment(p.vertices[k], p.vertices[l]);
                    if (s.slope != t.slope && s.intersects(t)) return true;
                }
            }
        } else if (other instanceof Circle c) {
            for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
                Segment s = new Segment(vertices[i], vertices[j]);
                if (c.intersects(s)) return true;
            }
        }
        return false;
    }

    /**
     * Calculates the area of the polygon using the shoelace formula
     *
     * @return The area of the polygon
     */
    @Override
    public double getArea() {
        double area = 0;
        for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            area += (vertices[j].getX() * vertices[i].getY()) - (vertices[i].getX() * vertices[j].getY());
        }
        return Math.abs(area) / 2.0;
    }

    /**
     * Calculates the minimum rectangle that contains all vertices of the polygon
     *
     * @return A Rectangle object representing the bounding box
     */
    @Override
    public Rectangle getBoundingBox() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Point p : vertices) {
            minX = Math.min(minX, p.getX());
            minY = Math.min(minY, p.getY());
            maxX = Math.max(maxX, p.getX());
            maxY = Math.max(maxY, p.getY());
        }
        return new Rectangle(new Point(minX, maxY), new Point(maxX, minY));
    }

    /**
     * Determines if a point is inside the polygon using ray casting algorithm
     *
     * @param point The point to check
     * @return True if the point is inside the polygon, false otherwise
     */
    @Override
    public boolean isPointInside(Point point) {
        int count = 0;
        for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            if (vertices[i].getY() > point.getY() != vertices[j].getY() > point.getY()) {
                double x = (double) ((vertices[j].getX() - vertices[i].getX()) * (point.getY() - vertices[i].getY())) / (vertices[j].getY() - vertices[i].getY()) + vertices[i].getX();
                if (point.getX() < x) count++;
            }
        }
        return count % 2 == 1;
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
