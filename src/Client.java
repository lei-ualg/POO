import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 7.0
 * @inv Point 0 ≤ θ ≤ 90 (first quadrant)
 * @inv Circle r > 0
 * @inv Circle P in the first quadrant
 * @inv Segment a != b
 * @inv Rectangle d1 = d2
 */
public class Client {
    /**
     * Problem H:<br>
     * Given the coordinates for a rectangle and a segment, determine if the segment intersects the rectangle.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            List<Point> points = new ArrayList<>();
            int n = 4;
            while (n-->0) {
                points.add(new Point(scanner.nextInt(), scanner.nextInt()));
            }
            Rectangle rect = new Rectangle(points);
            n = 2;
            List<Point> segment = new ArrayList<>();
            while (n-->0) {
                segment.add(new Point(scanner.nextInt(), scanner.nextInt()));
            }
            Segment seg = new Segment(segment.get(0), segment.get(1));
            scanner.close();
            System.out.println(rect.intersects(seg) ? 1 : 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            exit(0);
        }
    }
}
