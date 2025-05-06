public interface ICollider {
    /**
     * @return the centroid of the shape
     */
    Point centroid();

    void onUpdate();

    boolean isColliding(ICollider other);

    boolean isColliding(CollPoly other);

    boolean isColliding(CollCircle other);
}
