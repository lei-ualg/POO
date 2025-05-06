public class CollCircle implements ICollider {
    private double c_radius;
    private final double c_radiusOriginal;
    private final Point c_center;
    private final ITransform c_transform;

    public CollCircle(double radius, Point center, ITransform transform) {
        this.c_center = center;
        this.c_radius = radius;
        this.c_radiusOriginal = radius;
        this.c_transform = transform;
    }

    public CollCircle(String circle_string, ITransform transform) {
        this(Double.parseDouble(circle_string.split(" ")[2]), Utils.parsePoints(circle_string)[0], transform);
    }

    /**
     * Checks if the circle intersects with a segment.
     * Uses the following algorithm:
     * 1. Find the closest point on the segment to the center of the circle
     * 2. Calculate the projection of vector AP onto vector AB
     * 3. Clamp the projection to ensure the closest point is on the segment
     * 4. Calculate the coordinates of the closest point
     * 5. Check if the distance from the circle center to this point is less than or equal to radius
     *
     * @param segment the segment to check for intersection
     * @return true if the circle intersects with the segment, false otherwise
     * @see <a href="https://stackoverflow.com/questions/47481774/getting-point-on-line-segment-that-is-closest-to-another-point">Getting point on a line segment that is closest to another point [SO]</a>
     */
    public boolean intersects(Segment segment) {
        double ABx = segment.getB().getX() - segment.getA().getX();
        double ABy = segment.getB().getY() - segment.getA().getY();

        double APx = this.c_center.getX() - segment.getA().getX();
        double APy = this.c_center.getY() - segment.getA().getY();

        double AB_AB = ABx * ABx + ABy * ABy;
        double AP_AB = APx * ABx + APy * ABy;

        double t = Math.clamp(AP_AB / AB_AB, 0, 1);

        Point closest = new Point(segment.getA().getX() + (ABx * t), segment.getA().getY() + (ABy * t));
        return Utils.gt(this.c_radius, this.c_center.distance(closest));
    }

    /**
     * Checks if the circle intersects with another geometric form.
     * The method handles different types of geometric forms:
     * - For circles: Checks if the distance between centers is less than or equal to the sum of radii
     * - For other forms: Delegates to the other form's intersection detection
     *
     * @param p The other geometric form to check intersection with
     * @return True if the circle intersects with the other geometric form, false otherwise
     */
    public boolean intersects(CollPoly p) {
        return p.intersects(this);
    }

    /**
     * Checks if the circle intersects with another circle.
     * Uses the following algorithm:
     * 1. Calculate the distance between the centers of the circles
     * 2. Check if this distance is less than or equal to the sum of the radii
     *
     * @param c The other circle to check intersection with
     * @return True if the circles intersect, false otherwise
     */
    public boolean intersects(CollCircle c) {
        return !Utils.gt(this.c_center.distance(c.c_center), this.c_radius + c.c_radius);
    }

    /**
     * Calculates the area of the circle.
     * Uses the standard formula for circle area: πr² where r is the radius.
     *
     * @return The area of the circle as a double value
     */
    public double getArea() {
        return Math.PI * Math.pow(this.c_radius, 2);
    }

    /**
     * Returns the bounding box of the circle.
     *
     * @return the bounding box of the circle
     */
    public CollPoly getBoundingBox() {
        return new CollPoly(new Point(this.c_center.getX() - this.c_radius, this.c_center.getY() + this.c_radius),
                new Point(this.c_center.getX() + this.c_radius, this.c_center.getY() - this.c_radius), c_transform);
    }

    /**
     * Checks if a point is inside the circle.
     *
     * @param point the point to check
     * @return true if the point is inside the circle, false otherwise
     */
    public boolean isPointInside(Point point) {
        return Utils.gt(this.c_radius, this.c_center.distance(point));
    }

    @Override
    public Point centroid() {
        return c_center;
    }

    @Override
    public void onUpdate() {
        c_center.setX(c_transform.position().getX());
        c_center.setY(c_transform.position().getY());
        c_radius = c_radiusOriginal * c_transform.scale();
    }

    @Override
    public boolean isColliding(ICollider other) {
        return other.isColliding(this);
    }

    @Override
    public boolean isColliding(CollPoly other) {
        return other.isColliding(this);
    }

    @Override
    public boolean isColliding(CollCircle other) {
        boolean result;
        CollPoly boundingBox = Utils.sumBoundingBox(this.getBoundingBox(), other.getBoundingBox(), this.c_transform);
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
}
