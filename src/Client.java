import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.lang.System.exit;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 8.0
 * @inv Point 0 ≤ θ ≤ 90 (first quadrant)
 * @inv Circle r > 0
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
     * Problem J:<br>
     * Given a polygon or a subclass of it, take dx and dy and translate it.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Constructor<?> constructor;
        Class<?> cl;
        String s;
        String[] aos;
        GeometricForm p = null;
        while ((s = input.readLine()) != null) {
            if (s.isEmpty())
                break;
            aos = s.split(" ", 2);
            try {
                cl = getClass(aos[0]);
                constructor = cl.getConstructor(String.class);
                try {
                    p = (GeometricForm) constructor.newInstance(aos[1]);
                    String[] translate = input.readLine().split(" ", 2);
                    int dx = Integer.parseInt(translate[0]);
                    int dy = Integer.parseInt(translate[1]);
                    try {
                        p = p.translate(dx, dy);
                    } catch (IllegalArgumentException e) {
                        exitError(e.getMessage());
                    }
                } catch (InvocationTargetException e) {
                    // Verifica se a causa foi um IllegalArgumentException
                    if (e.getCause() instanceof IllegalArgumentException) {
                        exitError(e.getCause().getMessage());
                    }
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        input.close();
        if (p != null) {
            System.out.println(p);
        }
    }

    private static Class<?> getClass(String name) throws ClassNotFoundException {
        return switch (name) {
            case "Poligono" -> Polygon.class;
            case "Triangulo" -> Triangle.class;
            case "Retangulo" -> Rectangle.class;
            case "Circulo" -> Circle.class;
            default -> throw new ClassNotFoundException(name);
        };
    }

    private static void exitError(String message) {
        System.out.println(message);
        exit(0);
    }
}
