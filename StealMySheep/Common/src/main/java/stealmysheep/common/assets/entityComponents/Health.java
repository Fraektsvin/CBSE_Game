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
public class Health implements Component {

    private int maxHealth;
    private int health;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void damage(int amount) {
        this.health -= amount;
    }

    public boolean isDead() {
        if (this.health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }

}
