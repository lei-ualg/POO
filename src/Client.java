import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A client to manage the user input and output.
 *
 * @author Leonardo Albudane &amp; Mariana Afonso
 * @version 12.0
 */
public class Client {
    /**
     * Problem N:<br>
     * Given the gameObjects and their movements, detect the collisions between them after the frames.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        GameEngine engine = new GameEngine();



        input.close();
    }
}
