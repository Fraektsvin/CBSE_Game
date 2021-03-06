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

    private float swingTime, swingTimer, range;

    public MeleeWeapon(float swingTime, float range, String entityId, int damage) {
        super(entityId, damage);
        this.swingTime = swingTime;
        this.range = range;
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

    public void setSwingTime(float swingTime) {
        this.swingTime = swingTime;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
        if (this.swingTimer > 0) {
            this.swingTimer -= gameData.getDeltaTime();
        }
    }

}
