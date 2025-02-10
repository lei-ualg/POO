import java.util.Scanner;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 4.0
 * @inv Point 0 ≤ θ ≤ 90 (first quadrant)
 * @inv Circle r > 0
 * @inv Circle P in the first quadrant
 */
public class Client {
    /**
     * Problem C:<br>
     * Read <i>n</i> + 1 lines from the standard input. The first line contains the number <i>n</i> of points to read.<br>
     * The following <i>n</i> lines contain the polar coordinates of the points.<br>
     * After reading the points, print the path length between the first and the last point.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Point center = new Point(scanner.nextInt(), scanner.nextInt());
        Circle circle = new Circle(scanner.nextDouble(), center);
        scanner.close();
        System.out.println((int) circle.perimeter());
    }
}
