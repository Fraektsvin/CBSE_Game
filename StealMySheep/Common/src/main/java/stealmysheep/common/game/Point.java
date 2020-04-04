package stealmysheep.common.game;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

/**
 * An immutable data type for representing a point or vector in 2D space
 * @author frmik18
 */
public class Point {

    public final float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point multiply(float factor) {
        return new Point(x * factor, y * factor);
    }

    /**
     * @return a point with a magnitude of 1 or idempotent in the case of (0,0)
     */
    public Point unit() {
        float magnitude = (float) sqrt(pow(abs(x), 2) + pow(abs(y), 2));
        return new Point(x / magnitude, y / magnitude);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
