public interface IGameObject {
    /**
     * @return the name of the GameObject
     */
    String name();

    /**
     * @return the Transform of the GameObject
     */
    ITransform transform();

    /**
     * @return the visual shape of the GameObject used by the GUI
     */
    IShape shape();

    /**
     * @return the Collider of the GameObject with its centroid at this.transform().position()
     */
    ICollider collider();

    /**
     * @return the Behaviour of the GameObject, which defines how it reacts in the game (ex: move, shoot, flee, patrol)
     */
    IBehaviour behaviour();
}
