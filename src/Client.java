import java.util.Scanner;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 3.0
 * @inv Point 0 ≤ θ ≤ 90 (first quadrant)
 * @inv Point |r| &lt; 10 (distance from the origin)
 * @inv Path n ≥ 2
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
        int n = scanner.nextInt();
        Path path = new Path(n);
        for (int i = 0; i < n; i++) {
            path.addToPath(i, scanner.nextDouble(), scanner.nextDouble());
        }
        scanner.close();
        System.out.printf("%.2f", path.distance());
    }
}
