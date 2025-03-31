public class Transform implements ITransform {
    private final Point centroid;
    private int layer;
    private double angle;
    private double scale;

    public Transform(Point centroid, int layer, double angle, double scale) {
        this.centroid = centroid;
        this.layer = layer;
        this.angle = angle;
        this.scale = scale;
    }

    public void applyRotate(GeometricForm shape) {
        shape.rotate(angle);
    }

    public void applyScale(GeometricForm shape) {
        shape.scale(scale);
    }

    public void applyMove(GeometricForm shape) {
        shape.setCentroid(centroid);
    }

    public void applyAll(GeometricForm shape) {
        applyMove(shape);
        applyRotate(shape);
        applyScale(shape);
    }

    @Override
    public Point position() {
        return centroid;
    }

    @Override
    public void move(Point dPos, int dLayer) {
        this.centroid.translate(dPos);
        this.layer += dLayer;
    }

    @Override
    public void rotate(double dTheta) {
        this.angle += dTheta;
    }

    @Override
    public void scale(double dScale) {
        this.scale += dScale;
    }

    @Override
    public double scale() {
        return scale;
    }

    @Override
    public int layer() {
        return layer;
    }

    @Override
    public double angle() {
        return angle;
    }

    @Override
    public String toString() {
        return "%s %d %.2f %.2f".formatted(centroid, layer, angle, scale);
    }
}
