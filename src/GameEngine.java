import java.util.*;

/**
 * Game Engine class<br>
 * This class is responsible for managing the game objects and their movements and collisions.
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 1.0
 */
public class GameEngine {
    /**
     * List of game objects
     */
    private final List<GameObject> gObjects = new ArrayList<>();
    /**
     * Map of game objects and their movements
     */
    private final Map<GameObject, Transform> movementMap = new LinkedHashMap<>();

    /**
     * Adds a game object to the game engine.
     * @param go the game object to be added
     */
    public void add(GameObject go) {
        gObjects.add(go);
    }

    /**
     * Destroys a game object from the game engine.
     * @param go the game object to be destroyed
     */
    public void destroy(GameObject go) {
        gObjects.remove(go);
        movementMap.remove(go);
    }

    /**
     * Sets the movement of a game object.
     * @param go the game object to be moved
     * @param dx the x-coordinate of the movement
     * @param dy the y-coordinate of the movement
     * @param dLayer the layer of the movement
     * @param dAngle the angle of the movement
     * @param dScale the scale of the movement
     */
    public void setMovement(GameObject go, double dx, double dy, int dLayer, double dAngle, double dScale) {
        movementMap.put(go, new Transform(new Point(dx, dy), dLayer, dAngle, dScale));
    }

    /**
     * Getter for the list of game objects.
     * @return the list of game objects
     */
    public List<GameObject> getObjects() {
        return gObjects;
    }

    /**
     * Runs the game engine for one frame.
     */
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

    /**
     * Detects collisions between game objects.
     * @return a list of strings representing the collisions
     */
    public List<String> detectCollision() {
        Map<GameObject, List<String>> collisions = new LinkedHashMap<>();

        for (GameObject a : gObjects) {
            collisions.put(a, new ArrayList<>());
        }

        for (int i = 0; i < gObjects.size(); i++) {
            GameObject a = gObjects.get(i);
            for (int j = i + 1; j < gObjects.size(); j++) {
                GameObject b = gObjects.get(j);

                //alteração
                if (a.transform().layer() == b.transform().layer() && a.collider().isCollision(b.collider())) {
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

