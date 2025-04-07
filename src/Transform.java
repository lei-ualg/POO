/**
 * Transform class handles the spatial attributes of a GameObject in 2D space
 * It controls position, rotation, scaling, and layer placement
 * 
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 2.0
 */
public class Transform implements ITransform {
    private final Point centroid;
    private int layer;
    private double angle;
    private double scale;

    /**
     * Constructs a Transform with the given properties
     *
     * @param centroid the initial position (centroid) of the GameObject
     * @param layer the layer this GameObject resides in
     * @param angle the rotation angle in degrees (counter-clockwise from North)
     * @param scale the scale factor
     */
    public Transform(Point centroid, int layer, double angle, double scale) {
        this.centroid = centroid;
        this.layer = layer;
        this.angle = angle;
        this.scale = scale;
    }

    /**
     * Apply only the rotation to the GeometricForm
     * @param shape the GeometricForm to apply this ITransform to
     */
    public void applyRotate(GeometricForm shape) {
        shape.rotate(angle);
    }

    /**
     * Apply only the scaling to the GeometricForm
     * @param shape the GeometricForm to apply this ITransform to
     */
    public void applyScale(GeometricForm shape) {
        shape.scale(scale);
    }

    /**
     * Apply only the movement to the GeometricForm
     * @param shape the GeometricForm to apply this ITransform to
     */
    public void applyMove(GeometricForm shape) {
        shape.setCentroid(centroid);
    }

    /**
     * Apply all transformations to the GeometricForm
     * @param shape the GeometricForm to apply this ITransform to
     */
    public void applyAll(GeometricForm shape) {
        applyMove(shape);
        applyRotate(shape);
        applyScale(shape);
    }

    /**
     * @return the current position (centroid) of the GameObject
     */
    @Override
    public Point position() {
        return centroid;
    }
    
    /**
     * Moves the Transform by a given delta point and changes its layer
     *
     * @param dPos the differential Point to translate the centroid
     * @param dLayer the differential integer to adjust the layer
     */
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
