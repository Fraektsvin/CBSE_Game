/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.Point;

import java.time.Instant;

/**
 * @author frmik18
 */
public class Projectile extends Entity {

    private final int damage;
    /** Movement per second */
    private final Point delta;
    private final Instant expiry;
    private final Alignment alignment;

    public Projectile(int damage, Point delta, Instant expiry, Alignment alignment) {
        this.damage = damage;
        this.delta = delta;
        this.expiry = expiry;
        this.alignment = alignment;
    }

    public int getDamage() {
        return damage;
    }

    public Point getDelta() {
        return delta;
    }

    public Instant getExpiry() {
        return expiry;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public enum Alignment {
        PLAYER, ENEMIES
    }

}
