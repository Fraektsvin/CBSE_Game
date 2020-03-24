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
public class RangedWeapon extends Weapon {

    private int magazineSize;
    private float reloadTime, reloadTimer;
    private float shotCooldown, shotTimer;

    public RangedWeapon(int magazineSize, float reloadTime, float shotCooldown, String entityId, int damage) {
        super(entityId, damage);
        this.magazineSize = magazineSize;
        this.reloadTime = reloadTime;
        this.shotCooldown = shotCooldown;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public float getReloadTimer() {
        return reloadTimer;
    }

    public void setReloadTimer(float reloadTimer) {
        this.reloadTimer = reloadTimer;
    }

    public float getShotCooldown() {
        return shotCooldown;
    }

    public void setShotCooldown(float shotCooldown) {
        this.shotCooldown = shotCooldown;
    }

    public float getShotTimer() {
        return shotTimer;
    }

    public void setShotTimer(float shotTimer) {
        this.shotTimer = shotTimer;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }

}
