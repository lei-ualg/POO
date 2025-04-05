import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 11.0
 */
public class Client {
    /**
     * Problem L:<br>
     * Given a game object, print its information.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        GameEngine engine = new GameEngine();

        int f = Integer.parseInt(input.readLine());
        int n = Integer.parseInt(input.readLine());

        for (int i = 0; i < n; i++) {
            String name = input.readLine();

            // inout do transform
            String[] tParts = input.readLine().split(" ");
            double x = Double.parseDouble(tParts[0]);
            double y = Double.parseDouble(tParts[1]);
            int layer = Integer.parseInt(tParts[2]);
            double angle = Double.parseDouble(tParts[3]);
            double scale = Double.parseDouble(tParts[4]);
            Transform transform = new Transform(new Point(x, y), layer, angle, scale);

            // input do collider
            String[] cParts = input.readLine().split(" ");
            GeometricForm shape;
            if (cParts.length == 3) {
                double cx = Double.parseDouble(cParts[0]);
                double cy = Double.parseDouble(cParts[1]);
                double r = Double.parseDouble(cParts[2]);
                shape = new Circle(r, new Point(cx, cy));
            } else {
                shape = new Polygon(String.join(" ", cParts));
            }
            Collider collider = new Collider(transform, shape);

            GameObject go = new GameObject(name, transform, collider);
            engine.add(go);

            // movimentos???
            String[] mParts = input.readLine().split(" ");
            double dx = Double.parseDouble(mParts[0]);
            double dy = Double.parseDouble(mParts[1]);
            int dLayer = Integer.parseInt(mParts[2]);
            double dAngle = Double.parseDouble(mParts[3]);
            double dScale = Double.parseDouble(mParts[4]);

            engine.setMovement(go, dx, dy, dLayer, dAngle, dScale);
        }

        for (int i = 0; i < f; i++) {
            engine.frame();
        }

        for (GameObject go : engine.getObjects()) {
            go.transform().applyAll(go.collider().getForm());
        }

        // para ver a merda que estou a fazer
        System.out.println("----------");
        for (GameObject go : engine.getObjects()) {
            System.out.println(go);
        }

        List<String> collisions = engine.detectCollision();
        for (String collision : collisions) {
            System.out.println(collision);
        }

        input.close();
    }
}
