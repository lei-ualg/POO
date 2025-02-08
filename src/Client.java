import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A client class that creates points and prints the distance between them.
 *
 * @author Leonardo Albudane
 * @version 1.0
 * @inv none
 */
public class Client {
    private final List<Point> points = new ArrayList<>();


    /**
     * Constructs a client that reads two points from the user.
     */
    public Client() {
        Scanner scanner = new Scanner(System.in);
        int n = 2;
        for (int i = 0; i < n; i++) {
            double radius = scanner.nextDouble();
            double angle = scanner.nextDouble();
            this.points.add(new Point(radius, angle));
        }
        scanner.close();
    }

    public void problemA() {
        printDistanceBetweenPoints(this.points);
    }

    /**
     * Prints the int distance between each pair of points in the given list.
     *
     * @param points the list of points
     * @throws IllegalArgumentException if the list contains less than two points
     * @inv points.size() >= 2
     */
    public static void printDistanceBetweenPoints(List<Point> points) {
        if (points.size() < 2) {
            throw new IllegalArgumentException("At least two points are required");
        }
        for (int i = 0; i < points.size() - 1; i++) {
            System.out.print((int) points.get(i).distance(points.get(i + 1)));
        }
    }
}
