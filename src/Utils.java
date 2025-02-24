/**
 * Utility class for the project.
 * Epsilon is set to 1e-9.
 *
 * @author Leonardo Albudane
 * @version 1.0
 */
public class Utils {
    /**
     * The epsilon value for the comparison of double values.
     */
    public static final double epsilon = 1e-9;

    /**
     * Checks if two double values are equal.
     *
     * @param d the first double value
     * @param g the second double value
     * @return true if the values are equal based on the epsilon, false otherwise
     */
    public static boolean eq(double d, double g) {
        return Math.abs(d - g) < epsilon;
    }

    /**
     * Checks if the first double value is greater than the second double value.
     * @param d the first double value
     * @param g the second double value
     * @return true if the first value is greater than the second value, false otherwise
     */
    public static boolean gt(double d, double g) {
        return d - g > epsilon;
    }

    /**
     * Checks if the first double value is greater than or equal to the second double value.
     * @param d the first double value
     * @param g the second double value
     * @return true if the first value is greater than or equal to the second value, false otherwise
     */
    public static boolean ge(double d, double g) {
        return eq(d, g) || gt(d, g);
    }
}
