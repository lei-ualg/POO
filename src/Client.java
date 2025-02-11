import java.util.Scanner;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 5.0
 * @inv Point 0 ≤ θ ≤ 90 (first quadrant)
 * @inv Circle r > 0
 * @inv Circle P in the first quadrant
 * @inv Segment a != b
 */
public class Client {
    /**
     * Problem E:<br>
     * Given a circle with radius r and center C, and 2 points A and B, calculate if the circle intersects with the segment AB.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Point center = new Point(scanner.nextInt(), scanner.nextInt());
        Circle circle = new Circle(scanner.nextDouble(), center);
        Point a = new Point(scanner.nextInt(), scanner.nextInt());
        Point b = new Point(scanner.nextInt(), scanner.nextInt());
        Segment segment = new Segment(a, b);
        scanner.close();
        if (circle.intersects(segment)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}
