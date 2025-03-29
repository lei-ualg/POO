import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 10.0
 * @inv Point 0 ≤ θ ≤ 90 (first quadrant)
 * @inv Circle r > 0∏
 * @inv Circle P in the first quadrant
 * @inv Segment a != b
 * @inv Rectangle d1 = d2
 * @inv Triangle n = 3
 * @inv Polygon n > 2
 * @inv Polygon vertices are distinct
 * @inv Polygon vertices are not collinear
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
        String s;
        GameObject g = null;
        while ((s = input.readLine()) != null) {
            if (s.isEmpty())
                break;
            GeometricForm form;
            String[] aos = input.readLine().split(" ");
            double x = Double.parseDouble(aos[0]);
            double y = Double.parseDouble(aos[1]);
            Point p = new Point(x, y);
            int layer = Integer.parseInt(aos[2]);
            double angle = Double.parseDouble(aos[3]);
            double scale = Double.parseDouble(aos[4]);
            String shape = input.readLine();
            if (shape.split(" ").length == 3) {
                form  = new Circle(shape);
            } else {
                form = new Polygon(Utils.parsePoints(shape));
            }
            ITransform transform = new Transform(p, layer, angle, scale, form);
            ICollider collider = new Collider(transform, form);
            g = new GameObject(s, transform, collider);
        }
        input.close();
        System.out.println(g);
    }
}
