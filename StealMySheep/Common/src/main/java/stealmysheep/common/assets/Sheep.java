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
public class Sheep extends Entity {

    private float radius = 6;
    private boolean moving;
    
    public Sheep() {
        super();
    }

    public Sheep(String image) {
        super(image);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
}
