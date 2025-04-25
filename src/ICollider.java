public interface ICollider {
    /**
     * @return the centroid of the shape
     */
    Point centroid();

    void onUpdate();

    boolean isColliding(ICollider other);

    boolean isColliding(CollPoly other);

    bollean isColliding(CollCircle other);

    /**
     * @return the shape stored in the collider.
     */
    GeometricForm getForm();
}
