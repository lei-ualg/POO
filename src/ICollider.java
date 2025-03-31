public interface ICollider {
    /**
     * @return the centroid of the shape
     */
    Point centroid();

    GeometricForm getForm();
}