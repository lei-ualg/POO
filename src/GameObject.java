public class GameObject implements IGameObject {
    private final ICollider collider;
    private final ITransform transform;
    private final IShape shape;
    private final IBehaviour behaviour;
    private final String name;

    public GameObject(String name, ITransform transform, ICollider collider, IShape shape, IBehaviour behaviour) {
        this.name = name;
        this.transform = transform;
        this.collider = collider;
        this.shape = shape;
        this.behaviour = behaviour;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ITransform transform() {
        return transform;
    }

    @Override
    public IShape shape() {
        return shape;
    }

    @Override
    public ICollider collider() {
        return collider;
    }

    @Override
    public IBehaviour behaviour() {
        return behaviour;
    }

    @Override
    public String toString() {
        return """
                %s
                %s
                %s""".formatted(name(), transform(), collider());
    }
}
