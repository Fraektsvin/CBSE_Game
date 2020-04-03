/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.entityComponents;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.Point;

/**
 *
 * @author oscar
 */
public class Position implements Component {

    private float x, y;
    private float radians;

    public Position(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void plus(Point p) {
        x = x + p.x;
        y = y + p.y;
    }

    public void set(Point p) {
        x = p.x;
        y = p.y;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
    }
}
