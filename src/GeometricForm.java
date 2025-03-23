/**
 * Abstract class representing a geometric form
 */
public abstract class GeometricForm {

    /**
     * Translates the geometric form by dx and dy
     * @param dx the x-coordinate translation
     * @param dy the y-coordinate translation
     * @return the translated geometric form
     */
    abstract GeometricForm translate(int dx, int dy);

    /**
     * Checks if the geometric form intersects with another geometric form
     * @param that the other geometric form
     * @return true if the geometric form intersects with the other geometric form, false otherwise
     */
    abstract boolean intersects(GeometricForm that);

    /**
     * Checks if a point is inside the geometric form
     * @param p the point to check
     * @return true if the point is inside the geometric form, false otherwise
     */
    abstract boolean isPointInside(Point p);

    /**
     * Returns the area of the geometric form
     * @return the area of the geometric form
     */
    abstract double getArea();

    /**
     * Returns the bounding box of the geometric form
     * @return the bounding box of the geometric form
     */
    abstract Rectangle getBoundingBox();

    /**
     * Checks if the geometric form collides with another geometric form
     * @param that the other geometric form
     * @return true if the geometric form collides with the other geometric form, false otherwise
     */
    boolean collides(GeometricForm that) {
        boolean result;
        Rectangle boundingBox = Utils.sumBoundingBox(this.getBoundingBox(), that.getBoundingBox());
        double totalArea = this.getArea() + that.getArea();
        result = Utils.gt(totalArea, boundingBox.getArea());
        if (!result) {
            result = this.isPointInside(that.getBoundingBox().vertices[0]) || that.isPointInside(this.getBoundingBox().vertices[0]);
        }
        return result || this.intersects(that);
    }
}
