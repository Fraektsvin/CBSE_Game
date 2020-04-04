/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

import stealmysheep.common.game.Point;

/**
 * @author frmik18
 */
public class Projectile extends Entity {

    private final int damage;
    /** Movement per second */
    private final Point deltaMovement;
    private float expiry;
    private final Alignment alignment;

    public Projectile(int damage, Point deltaMovement, float expiry, Alignment alignment) {
        this.damage = damage;
        this.deltaMovement = deltaMovement;
        this.expiry = expiry;
        this.alignment = alignment;
    }

    public int getDamage() {
        return damage;
    }

    public Point getDeltaMovement() {
        return deltaMovement;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public float getExpiry() {
        return expiry;
    }

    public void decrementExpiry(float amount) {
        expiry -= amount;
    }

    public enum Alignment {
        PLAYER, ENEMIES
    }

}
