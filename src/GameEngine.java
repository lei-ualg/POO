import java.util.*;

/**
 * Game Engine class<br>
 * This class is responsible for managing the game objects and their movements and collisions.
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 1.0
 */
public class GameEngine implements IGameEngine {
    private final List<IGameObject> enabledGo = new ArrayList<>();
    private final List<IGameObject> disabledGo = new ArrayList<>();
    private final Map<IGameObject, IBehaviour> behaviourMap = new LinkedHashMap<>();

    @Override
    public void addEnabled(IGameObject go) {
        enabledGo.add(go);
    }

    @Override
    public void addDisabled(IGameObject go) {
        disabledGo.remove(go);
    }

    @Override
    public void enable(IGameObject go) {
        if (disabledGo.contains(go)) {
            disabledGo.remove(go);
            enabledGo.add(go);
        }
    }

    @Override
    public void disable(IGameObject go) {
        if (enabledGo.contains(go)) {
            enabledGo.remove(go);
            disabledGo.add(go);
        }
    }

    @Override
    public boolean isEnabled(IGameObject go) {
        return enabledGo.contains(go);
    }

    @Override
    public boolean isDisabled(IGameObject go) {
        return disabledGo.contains(go);
    }

    @Override
    public List<IGameObject> getEnabled() {
        return List.copyOf(enabledGo);
    }

    @Override
    public List<IGameObject> getDisabled() {
        return List.copyOf(disabledGo);
    }

    @Override
    public void destroy(IGameObject go) {
        if (enabledGo.contains(go)) {
            enabledGo.remove(go);
        } else disabledGo.remove(go);
    }

    @Override
    public void destroyAll() {
        for (IGameObject go : enabledGo) {
            go.behaviour().onDestroy();
        }
        for (IGameObject go : disabledGo) {
            go.behaviour().onDestroy();
        }
        enabledGo.clear();
        disabledGo.clear();
    }

    @Override
    public void run() {

    }

    @Override
    public void checkCollisions() {
        Map<IGameObject, List<IGameObject>> collisions = new LinkedHashMap<>();

        for (IGameObject a : enabledGo) {
            collisions.put(a, new ArrayList<>());
        }

        for (int i = 0; i < enabledGo.size(); i++) {
            IGameObject a = enabledGo.get(i);
            for (int j = i + 1; j < enabledGo.size(); j++) {
                IGameObject b = enabledGo.get(j);

                //alteração
                if (a.transform().layer() == b.transform().layer() && a.collider().isColliding(b.collider())) {
                    collisions.get(a).add(b);
                    collisions.get(b).add(a);
                }
            }
        }

        for (Map.Entry<IGameObject, List<IGameObject>> entry : collisions.entrySet()) {
            IGameObject go = entry.getKey();
            List<IGameObject> collidedObjects = entry.getValue();

            if (!collidedObjects.isEmpty()) {
                go.behaviour().onCollision(collidedObjects);
            }
        }
    }
}

