/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.entityComponents;

import assets.Entity;
import game.GameData;

/**
 *
 * @author oscar
 */
public class Health implements Component {

    private int maxHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }

}
