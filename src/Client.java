import java.util.Scanner;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane
 * @version 1.0
 */
public class Client {
    /**
     * Problem A:<br>
     * Write a client class that creates two {@link Point}s and prints the distance between them.
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
