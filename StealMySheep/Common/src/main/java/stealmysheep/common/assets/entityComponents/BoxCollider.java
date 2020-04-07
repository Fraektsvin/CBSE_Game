/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.entityComponents;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.GameData;

import java.util.Collection;

/**
 *
 * @author oscar
 */
public class BoxCollider implements Component {

    private float height, width;
    private Collection<Entity> collidesWith;
    private boolean collTop = false;
    private boolean collRight = false;
    private boolean collBottom = false;
    private boolean collLeft = false;

    public BoxCollider(float height, float width) {
        this.height = height;
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean collidesTop() {
        return collTop;
    }

    public boolean collidesRight() {
        return collRight;
    }

    public boolean collidesBottom() {
        return collBottom;
    }

    public boolean collidesLeft() {
        return collLeft;
    }

    public void updateCollisions(Collection<Entity> entities,
                                 boolean top, boolean right, boolean bottom, boolean left) {
        collidesWith = entities;
        collTop = top;
        collRight = right;
        collBottom = bottom;
        collLeft = left;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }

}
