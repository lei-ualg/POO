import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 12.0
 */
public class Client {
    /**
     * Problem N:<br>
     * Given the gameObjects and their movements, detect the collisions between them after the frames.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        GameEngine engine = new GameEngine();

        int f = Integer.parseInt(input.readLine());
        int n = Integer.parseInt(input.readLine());

        while (n-->0) {
            String name = input.readLine();

            // Transform
            String[] transformData = input.readLine().split(" ");
            double x = Double.parseDouble(transformData[0]);
            double y = Double.parseDouble(transformData[1]);
            int layer = Integer.parseInt(transformData[2]);
            double angle = Double.parseDouble(transformData[3]);
            double scale = Double.parseDouble(transformData[4]);
            Transform transform = new Transform(new Point(x, y), layer, angle, scale);

            // Collider
            String[] colliderData = input.readLine().split(" ");
            GeometricForm shape;
            if (colliderData.length == 3) {
                double cx = Double.parseDouble(colliderData[0]);
                double cy = Double.parseDouble(colliderData[1]);
                double r = Double.parseDouble(colliderData[2]);
                shape = new Circle(r, new Point(cx, cy));
            } else {
                shape = new Polygon(String.join(" ", colliderData));
            }

            Collider collider = new Collider(transform, shape);

            // GameObject
            GameObject go = new GameObject(name, transform, collider);
            engine.add(go);

            // Movement
            String[] movementData = input.readLine().split(" ");
            double dx = Double.parseDouble(movementData[0]);
            double dy = Double.parseDouble(movementData[1]);
            int dLayer = Integer.parseInt(movementData[2]);
            double dAngle = Double.parseDouble(movementData[3]);
            double dScale = Double.parseDouble(movementData[4]);

            engine.setMovement(go, dx, dy, dLayer, dAngle, dScale);
        }

        while (f-->0) {
            engine.frame();
        }
        for (GameObject go : engine.getObjects()) {
            go.transform().applyAll(go.collider().getForm());
        }

        for (String collision : engine.detectCollision()) {
            System.out.println(collision);
        }

        input.close();
    }
}
