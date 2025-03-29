public class Collider implements ICollider {
    private final ITransform transform;
    private final GeometricForm shape;

    public Collider(ITransform transform, GeometricForm shape) {
        this.transform = transform;
        this.shape = shape;
    }

    @Override
    public Point centroid() {
        return this.transform.position();
    }

    @Override
    public String toString() {
        return "%s".formatted(shape);
    }
}
