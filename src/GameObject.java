public class GameObject implements IGameObject {
    private final ICollider collider;
    private final ITransform transform;
    private final String name;

    public GameObject(String name, ITransform transform, ICollider collider) {
        this.name = name;
        this.transform = transform;
        this.collider = collider;
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
    public ICollider collider() {
        return collider;
    }

    @Override
    public String toString() {
        return """
                %s
                %s
                %s""".formatted(name(), transform(), collider());
    }
}
