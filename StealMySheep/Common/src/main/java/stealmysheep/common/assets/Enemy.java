/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

/**
 *
 * @author oscar
 */
public class Enemy extends Entity {

    private Entity target;
    private boolean targetPlayer, targetSheep;
    private float targetRadius;

    public Enemy() {
        super();
    }

    public Enemy(boolean targetPlayer, boolean targetSheep, float targetRadius) {
        super();
        this.targetPlayer = targetPlayer;
        this.targetSheep = targetSheep;
        this.targetRadius = targetRadius;
    }

    public Enemy(boolean targetPlayer, boolean targetSheep, float targetRadius, String image) {
        super(image);
        this.targetPlayer = targetPlayer;
        this.targetSheep = targetSheep;
        this.targetRadius = targetRadius;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public boolean willTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(boolean targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public boolean willTargetSheep() {
        return targetSheep;
    }

    public void setTargetSheep(boolean targetSheep) {
        this.targetSheep = targetSheep;
    }

    public float getTargetRadius() {
        return targetRadius;
    }

    public void setTargetRadius(float targetRadius) {
        this.targetRadius = targetRadius;
    }

}
