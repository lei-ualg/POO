import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class CollPoly implements ICollider {
    public Point[] verticesOriginal;
    public Point[] vertices;
    public ITransform transform;

    /**
     * Constructor for the Polygon class
     * The Polygon constructor doesn't have any invariants methods, because the complexity of the code is already O(n^2)
     *
     * @param points_string The vertices of the polygon in string format
     */
    public CollPoly(String points_string, ITransform transform) {
        this(Utils.parsePoints(points_string), transform);
    }

    /**
     * Constructor for the Polygon class
     * The Polygon constructor doesn't have any invariants methods, because the complexity of the code is already O(n^2)
     *
     * @param points The vertices of the polygon
     */
    public CollPoly(Point[] points, ITransform transform) {
        if (points.length < 3) throw new IllegalArgumentException("Poligono:vi");
        Point[] vertices = new Point[points.length];
        Point[] verticesOriginal = new Point[points.length];
        Segment[] sides = new Segment[points.length];
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point q = points[(i + 1) % points.length];
            Point r = points[(i + 2) % points.length];
            vertices[i] = p;
            verticesOriginal[i] = new Point(p.getX(), p.getY());
            Segment seg = new Segment(p, q);
            if (new Line(p, q).has(r)) throw new IllegalArgumentException("Poligono:vi"); // colinearity
            sides[i] = seg;
            for (int j = 0; j < i; j++) {
                if (seg.intersects(sides[j])) throw new IllegalArgumentException("Poligono:vi");
            }
        }
        this.vertices = vertices;
        this.verticesOriginal = verticesOriginal;
        this.transform = transform;
    }

    /**
     * Constrcts a rectangle with only the top-left and bottom-right corners.
     *
     * @param topLeft     the top-left point
     * @param bottomRight the bottom-right point
     */
    public CollPoly(Point topLeft, Point bottomRight, ITransform transform) {
        this(new Point[]{
                topLeft,
                new Point(topLeft.getX(), bottomRight.getY()),
                bottomRight,
                new Point(bottomRight.getX(), topLeft.getY())
        }, transform);
    }

    /**
     * Translates the polygon by dx and dy
     *
     * @param dx The x translation
     * @param dy The y translation
     */
    public void translate(double dx, double dy) {
        for (int i = 0; i < vertices.length; i++) {
            verticesOriginal[i].translate(dx, dy);
        }
    }

    @Override
    public Point centroid() {
        int n = vertices.length;
        double A = 0;
        double Cx = 0, Cy = 0;

        for (int i = 0; i < n - 1; i++) {
            double cross = vertices[i].getX() * vertices[i + 1].getY() - vertices[i + 1].getX() * vertices[i].getY();
            A += cross;
            Cx += (vertices[i].getX() + vertices[i + 1].getX()) * cross;
            Cy += (vertices[i].getY() + vertices[i + 1].getY()) * cross;
        }

        double cross = vertices[n - 1].getX() * vertices[0].getY() - vertices[0].getX() * vertices[n - 1].getY();
        A += cross;
        Cx += (vertices[n - 1].getX() + vertices[0].getX()) * cross;
        Cy += (vertices[n - 1].getY() + vertices[0].getY()) * cross;

        A *= 0.5;
        Cx /= (6 * A);
        Cy /= (6 * A);

        return new Point(Cx, Cy);
    }


    /**
     * Calculates the area of the polygon using the shoelace formula
     *
     * @return The area of the polygon
     */
    public double getArea() {
        int n = vertices.length;
        double A = 0;

        for (int i = 0; i < n - 1; i++) {
            A += vertices[i].getX() * vertices[i + 1].getY() - vertices[i + 1].getX() * vertices[i].getY();
        }

        // Closing the polygon
        A += vertices[n - 1].getX() * vertices[0].getY() - vertices[0].getX() * vertices[n - 1].getY();

        return Math.abs(A) * 0.5;
    }

    /**
     * Calculates the minimum rectangle that contains all vertices of the polygon
     *
     * @return A Rectangle object representing the bounding box
     */
    public CollPoly getBoundingBox() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Point p : vertices) {
            minX = Math.min(minX, p.getX());
            minY = Math.min(minY, p.getY());
            maxX = Math.max(maxX, p.getX());
            maxY = Math.max(maxY, p.getY());
        }
        return new CollPoly(new Point(minX, maxY), new Point(maxX, minY), transform);
    }

    /**
     * Determines if a point is inside the polygon using ray casting algorithm
     *
     * @param point The point to check
     * @return True if the point is inside the polygon, false otherwise
     */
    public boolean isPointInside(Point point) {
        int count = 0;
        for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            if (vertices[i].getY() > point.getY() != vertices[j].getY() > point.getY()) {
                double x = (vertices[j].getX() - vertices[i].getX()) * (point.getY() - vertices[i].getY()) / (vertices[j].getY() - vertices[i].getY()) + vertices[i].getX();
                if (point.getX() < x) count++;
            }
        }
        return count % 2 == 1;
    }

    public boolean intersects(CollCircle c) {
        for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            Segment s = new Segment(vertices[i], vertices[j]);
            if (c.intersects(s)) return true;
        }
        return false;
    }

    /**
     * Checks if this polygon intersects with another geometric form
     *
     * @param p The other geometric form to check intersection with
     * @return True if the polygon intersects with the other geometric form, false otherwise
     */
    public boolean intersects(CollPoly p) {
        for (int i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            Segment s = new Segment(vertices[i], vertices[j]);
            for (int k = 0, l = p.vertices.length - 1; k < p.vertices.length; l = k++) {
                Segment t = new Segment(p.vertices[k], p.vertices[l]);
                if (s.slope != t.slope && s.intersects(t)) return true;
            }
        }
        return false;
    }

    @Override
    public void onUpdate() {
        Point centroid = centroid();
        double dx = transform.position().getX() - centroid.getX();
        double dy = transform.position().getY() - centroid.getY();
        translate(dx, dy);
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Point(verticesOriginal[i].getX(), verticesOriginal[i].getY());
        }
        for (Point p : vertices) {
            p.rotate(transform.angle(), centroid);
        }
        for (Point p : vertices) {
            p.scale(transform.scale(), centroid);
        }
    }

    @Override
    public boolean isColliding(ICollider other) {
        return other.isColliding(this);
    }

    @Override
    public boolean isColliding(CollPoly other) {
        boolean result;
        CollPoly boundingBox = Utils.sumBoundingBox(this.getBoundingBox(), other.getBoundingBox(), this.transform);
        double totalArea = this.getArea() + other.getArea();
        result = Utils.gt(totalArea, boundingBox.getArea());
        if (!result) {
            result = this.isPointInside(other.getBoundingBox().vertices[0]) || other.isPointInside(this.getBoundingBox().vertices[0]);
        }
        if (!result) {
            result = this.intersects(other);
        }
        return result;
    }

    @Override
    public boolean isColliding(CollCircle other) {
        boolean result;
        CollPoly boundingBox = Utils.sumBoundingBox(this.getBoundingBox(), other.getBoundingBox(), this.transform);
        double totalArea = this.getArea() + other.getArea();
        result = Utils.gt(totalArea, boundingBox.getArea());
        if (!result) {
            result = this.isPointInside(other.getBoundingBox().vertices[0]) || other.isPointInside(this.getBoundingBox().vertices[0]);
        }
        if (!result) {
            result = this.intersects(other);
        }
        return result;
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
        if (!(o instanceof CollPoly poligono)) return false;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point p : vertices) {
            sb.append(p.toString()).append(" ");
        }
        return sb.toString();
    }
}
