public interface ICollider {
    /**
     * @return the centroid of the shape
     */
    Point centroid();

    /**
     * @return the shape stored in the collider.
     */
    GeometricForm getForm();

    /**
     * Check if this collider collides with another collider
     * @param other the other collider
     * @return true if the colliders collide, false otherwise
     */
    boolean collidesWith(ICollider other);
}