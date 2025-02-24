import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 6.0
 * @inv Point 0 ≤ θ ≤ 90 (first quadrant)
 * @inv Circle r > 0
 * @inv Circle P in the first quadrant
 * @inv Segment a != b
 * @inv Rectangle d1 = d2
 */
public class Client {
    /**
     * Problem G:<br>
     * Given four points in the plane, determine if they form a rectangle.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            List<Point> points = new ArrayList<>();
            int n = 4;
            while (n-- > 0) {
                points.add(new Point(scanner.nextInt(), scanner.nextInt()));
            }
            scanner.close();
            System.out.println(new Rectangle(points));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            exit(0);
        }
    }
}
