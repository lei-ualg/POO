import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 10.0
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
     * Problem K:<br>
     * Given up to 4 geometric forms, check if any of them collides with the others.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Constructor<?> constructor;
        Class<?> cl;
        String s;
        String[] aos;
        List<GeometricForm> forms = new ArrayList<>();
        boolean collides = false;
        while ((s = input.readLine()) != null) {
            if (s.isEmpty())
                break;
            aos = s.split(" ", 2);
            try {
                cl = getClass(aos[0]);
                constructor = cl.getConstructor(String.class);
                try {
                    GeometricForm p = (GeometricForm) constructor.newInstance(aos[1]);
                    for (int i = 0; i < forms.size(); i++) {
                        if (forms.get(i).collides(p)) {
                            System.out.println("Colisao na posicao " + i);
                            collides = true;
                            break;
                        }
                    }
                    if (collides) break;
                    forms.add(p);
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
        if (!collides) {
            System.out.println("Sem colisoes");
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
