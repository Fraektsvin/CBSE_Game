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
    private float attackRadius;
    private boolean moving;

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

    public Enemy(boolean targetPlayer, boolean targetSheep, float targetRadius, float attackRadius, String image) {
        super(image);
        this.targetPlayer = targetPlayer;
        this.targetSheep = targetSheep;
        this.targetRadius = targetRadius;
        this.attackRadius = attackRadius;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    public void setAttackRadius(float attackRadius) {
        this.attackRadius = attackRadius;
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

}
