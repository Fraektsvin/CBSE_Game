/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.entityComponents;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.GameData;

/**
 *
 * @author oscar
 */
public class MeleeWeapon extends Weapon {

    private final float swingTime;
    /** How wide to swing, in radians */
    private double swingArc;
    private final float radius;
    private float swingTimer;

    public MeleeWeapon(float swingTime, float radius, String entityId, int damage, double swingArc) {
        super(entityId, damage);
        this.swingTime = swingTime;
        this.radius = radius;
        this.swingArc = swingArc;
    }

    public float getSwingTime() {
        return swingTime;
    }

    public float getSwingTimer() {
        return swingTimer;
    }

    public void setSwingTimer(float swingTimer) {
        this.swingTimer = swingTimer;
    }

    public double getSwingArc() {
        return swingArc;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }

}
