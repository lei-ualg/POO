public interface ITransform {

    /**
     * Apply this ITransform to the GeometricForm
     *
     * @param shape the GeometricForm to apply this ITransform to
     */
    void applyRotate(GeometricForm shape);

    /**
     * Apply this ITransform to the GeometricForm
     *
     * @param shape the GeometricForm to apply this ITransform to
     */
    void applyScale(GeometricForm shape);

    /**
     * Apply this ITransform to the GeometricForm
     *
     * @param shape the GeometricForm to apply this ITransform to
     */
    void applyMove(GeometricForm shape);

    /**
     * Apply this ITransform to the GeometricForm
     * @param shape the GeometricForm to apply this ITransform to
     */
    void applyAll(GeometricForm shape);

    /**
     * Move this ITransform by dPos.x(), dPos.y() and dlayer
     *
     * @param dPos   the 2D differential to move
     * @param dlayer the layer differential
     */
    void move(Point dPos, int dlayer);

    /**
     * Rotate this ITransform from current orientation by dTheta
     *
     * @param dTheta pos: 0 <= this.angle() < 360
     */
    void rotate(double dTheta);

    /**
     * increment the ITransform scale by dscale
     *
     * @param dScale the scale increment
     */
    void scale(double dScale);

    /**
     * @return the (x,y) coordinates
     */
    Point position();

    /**
     * @return the layer
     */
    int layer();

    /**
     * @return the angle in degrees
     */
    double angle();

    /**
     * @return the current scale factor
     */
    double scale();

}