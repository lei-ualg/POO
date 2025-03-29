/**
 * Abstract class representing a geometric form
 */
public abstract class GeometricForm {
    /**
     * Translates the geometric form by dx and dy
     *
     * @param dx the x-coordinate translation
     * @param dy the y-coordinate translation
     */
    abstract void translate(double dx, double dy);

    /**
     * Checks if the geometric form intersects with another geometric form
     *
     * @param that the other geometric form
     * @return true if the geometric form intersects with the other geometric form, false otherwise
     */
    abstract boolean intersects(Polygon that);

    abstract boolean intersects(Circle that);

    /**
     * Checks if a point is inside the geometric form
     *
     * @param p the point to check
     * @return true if the point is inside the geometric form, false otherwise
     */
    abstract boolean isPointInside(Point p);

    /**
     * Returns the area of the geometric form
     *
     * @return the area of the geometric form
     */
    abstract double getArea();

    /**
     * Returns the bounding box of the geometric form
     *
     * @return the bounding box of the geometric form
     */
    abstract Polygon getBoundingBox();

    /**
     * Returns the centroid of the geometric form
     *
     * @return the centroid of the geometric form
     */
    abstract Point getCentroid();

    /**
     * Sets the centroid of the geometric form
     *
     * @param p the new centroid
     */
    abstract void setCentroid(Point p);

    /**
     * Rotates the geometric form around the centroid
     *
     * @param dTheta the angle to rotate the geometric form
     */
    abstract void rotate(double dTheta);

    /**
     * Scale the geometric form by the given factor
     *
     * @param dScale the scale factor to scale the geometric form
     */
    abstract void scale(double dScale);

    /**
     * Checks if the geometric form collides with another geometric form
     *
     * @param that the other geometric form
     * @return true if the geometric form collides with the other geometric form, false otherwise
     */
    boolean collides(GeometricForm that) {
        boolean result;
        Polygon boundingBox = Utils.sumBoundingBox(this.getBoundingBox(), that.getBoundingBox());
        double totalArea = this.getArea() + that.getArea();
        result = Utils.gt(totalArea, boundingBox.getArea());
        if (!result) {
            result = this.isPointInside(that.getBoundingBox().vertices[0]) || that.isPointInside(this.getBoundingBox().vertices[0]);
        }
        if (!result) {
            if (that instanceof Polygon) {
                result = this.intersects((Polygon) that);
            } else if (that instanceof Circle) {
                result = this.intersects((Circle) that);
            }
        }
        return result;
    }
}
