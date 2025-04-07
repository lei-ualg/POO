/**
 * Collider class represents a geometric form used for collision detection
 *
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 3.0
 */
public class Collider implements ICollider {
    private final ITransform transform;
    private final GeometricForm shape;

    public Collider(ITransform transform, GeometricForm shape) {
        this.transform = transform;
        this.shape = shape;
    }

    @Override
    public GeometricForm getForm() {
        return this.shape;
    }

    @Override
    public Point centroid() {
        return this.transform.position();
    }

    @Override
    public boolean collidesWith(ICollider other) {
        if (!(other instanceof Collider o)) return false;
        if (this.transform.layer() != o.transform.layer()) return false;
        return this.shape.collides(o.getForm());
    }

    /**
     * Returns the string representation of the collider
     *
     * @return The string representation of the collider
     */
    @Override
    public String toString() {
        return "%s".formatted(shape);
    }
}
