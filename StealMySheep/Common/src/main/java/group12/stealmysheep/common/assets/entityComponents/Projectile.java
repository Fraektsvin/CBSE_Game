/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.common.assets.entityComponents;

import group12.stealmysheep.common.assets.Entity;
import group12.stealmysheep.common.game.GameData;

/**
 *
 * @author oscar
 */
public class Projectile implements Component {

    private String sourceId;
    private int damage;
    private float lifetime, lifeTimer;

    public Projectile(String sourceId, int damage, float lifetime) {
        this.sourceId = sourceId;
        this.damage = damage;
        this.lifetime = lifetime;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getLifetime() {
        return lifetime;
    }

    public void setLifetime(float lifetime) {
        this.lifetime = lifetime;
    }

    public float getLifeTimer() {
        return lifeTimer;
    }

    public void setLifeTimer(float lifeTimer) {
        this.lifeTimer = lifeTimer;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }

}
