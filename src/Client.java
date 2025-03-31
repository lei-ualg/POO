import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

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
            ITransform transform = new Transform(p, layer, angle, scale);
            ICollider collider = new Collider(transform, form);
            g = new GameObject(s, transform, collider);
            String s2;
            while ((s2 = input.readLine()) != null && !s2.isEmpty()) {
                String[] aos2 = s2.trim().split(" ");
                switch (aos2[0]) {
                    case "move" -> {
                        double dx = Double.parseDouble(aos2[1]);
                        double dy = Double.parseDouble(aos2[2]);
                        int dLayer = Integer.parseInt(aos2[3]);
                        g.transform().move(new Point(dx, dy), dLayer);
                    }
                    case "rotate" -> {
                        double dTheta = Double.parseDouble(aos2[1]);
                        g.transform().rotate(dTheta);
                    }
                    case "scale" -> {
                        double dScale = Double.parseDouble(aos2[1]);
                        g.transform().scale(dScale);
                    }
                }
            }
        }
        input.close();
        assert g != null;
        g.transform().applyAll(g.collider().getForm());
        System.out.println(g);
    }
}
