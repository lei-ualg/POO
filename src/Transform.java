/**
 * Transform class handles the spatial attributes of a GameObject in 2D space
 * It controls position, rotation, scaling, and layer placement
 *
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 2.0
 */
public class Transform implements ITransform {
    private Point centroid;
    private double angle;
    private double scale;
    private int layer;
    ICollider collider;

    @Override
    public void move(Point dPos, int dlayer) {
        this.centroid.translate(dPos.getX(), dPos.getY());
        this.layer += dlayer;
    }

    @Override
    public void rotate(double dTheta) {
        this.angle += dTheta;
        this.angle = this.angle % 360;
    }

    @Override
    public void scale(double dScale) {
        this.scale += dScale;
    }

    @Override
    public Point position() {
        return this.centroid;
    }

    @Override
    public int layer() {
        return this.layer;
    }

    @Override
    public double angle() {
        return this.angle;
    }

    @Override
    public double scale() {
        return this.scale;
    }
}
