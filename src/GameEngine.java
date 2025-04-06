import java.util.*;

public class GameEngine {
    private final List<GameObject> gObjects = new ArrayList<>();
    private final Map<GameObject, Transform> movementMap = new LinkedHashMap<>();

    public void add(GameObject go) {
        gObjects.add(go);
    }

    public void destroy(GameObject go) {
        gObjects.remove(go);
        movementMap.remove(go);
    }

    public void setMovement(GameObject go, double dx, double dy, int dLayer, double dAngle, double dScale) {
        movementMap.put(go, new Transform(new Point(dx, dy), dLayer, dAngle, dScale));
    }

    public List<GameObject> getObjects() {
        return gObjects;
    }

    public void frame() {
        for (GameObject obj : gObjects) {
            Transform movement = movementMap.get(obj);
            if (movement != null) {
                obj.transform().move(movement.position(), movement.layer());
                obj.transform().rotate(movement.angle());
                obj.transform().scale(movement.scale());
            }
        }
    }

    public List<String> detectCollision() {
            Map<GameObject, List<String>> collisions = new LinkedHashMap<>();

            for (GameObject a : gObjects) {
                collisions.put(a, new ArrayList<>());
            }

            for (int i = 0; i < gObjects.size(); i++) {
                GameObject a = gObjects.get(i);
                for (int j = i + 1; j < gObjects.size(); j++) {
                    GameObject b = gObjects.get(j);
                    if (a.collider().collidesWith(b.collider())) {
                        collisions.get(a).add(b.name());
                        collisions.get(b).add(a.name());
                    }
                }
            }

            List<String> result = new ArrayList<>();
            for (Map.Entry<GameObject, List<String>> entry : collisions.entrySet()) {
                List<String> colliders = entry.getValue();
                if (!colliders.isEmpty()) {
                    result.add(entry.getKey().name() + " " + String.join(" ", colliders));
                }
            }

            return result;
        }
}

