import java.util.Scanner;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 2.0
 * @inv 0 ≤ θ ≤ 90 (first quadrant)
 * @inv |r| < 10 (distance from the origin)
 */
public class Client {
    /**
     * Problem B:<br>
     * Write a client class that creates two {@link Point}s and prints the distance between them.
     * Now, the Point class has been updated to include an invariant that checks if the points are in the first quadrant.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Point p1 = new Point(scanner.nextDouble(), scanner.nextDouble());
        Point p2 = new Point(scanner.nextDouble(), scanner.nextDouble());
        System.out.print((int) p1.distance(p2));
        scanner.close();
    }
}
