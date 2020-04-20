/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

/**
 *
 * @author nadinfariss
 */
public class Projectile extends Entity {

    private float Speed;

    public Projectile(float Speed) {
        this.Speed = Speed;
    }

    public Projectile(float Speed, String image) {
        super(image);
        this.Speed = Speed;

    }

    public float getSpeed() {
        return Speed;
    }

    public void setSpeed(float Speed) {
        this.Speed = Speed;
    }

}
