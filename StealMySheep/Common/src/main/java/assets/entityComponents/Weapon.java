/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.entityComponents;

/**
 *
 * @author oscar
 */
public abstract class Weapon implements Component {

    private String entityId;
    private int damage;
    private boolean isAttacking;

    public Weapon(String entityId, int damage) {
        this.entityId = entityId;
        this.damage = damage;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isIsAttacking() {
        return isAttacking;
    }

    public void setIsAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

}
