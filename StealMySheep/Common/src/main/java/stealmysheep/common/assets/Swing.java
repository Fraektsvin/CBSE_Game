package stealmysheep.common.assets;

import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;

/**
 * The swing of a MeleeWeapon
 *
 * @author frmik18
 */
public class Swing extends Entity {

    /** How long we have been swinging for */
    private double swingTime = 0;
    /** The total swing time */
    private final double maxSwingTime;
    /** Distance from origin */
    private final double radius;
    /** The arc in radians */
    private final double arc;
    /** The origin, i.e. the wielder's position */
    private final Position origin;

    public Swing(double maxSwingTime, double radius, double arc, Position origin) {
        this.maxSwingTime = maxSwingTime;
        this.radius = radius;
        this.arc = arc;
        this.origin = origin;
    }

    public void updateSwingTime(GameData data) {
        swingTime += data.getDeltaTime();
    }

    public double getSwingTime() {
        return swingTime;
    }

    public double getMaxSwingTime() {
        return maxSwingTime;
    }

    public double getRadius() {
        return radius;
    }

    public double getArc() {
        return arc;
    }

    public Position getOrigin() {
        return origin;
    }
}
