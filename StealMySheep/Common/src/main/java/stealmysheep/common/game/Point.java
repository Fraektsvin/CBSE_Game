package stealmysheep.common.game;

/**
 * An immutable data type for representing a point in 2D space
 * @author frmik18
 */
public class Point {

    public final float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point times(float factor) {
        return new Point(x * factor, y * factor);
    }
}
