/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.entityComponents;

import assets.Entity;
import game.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author oscar
 */
public class Movement implements Component {

    private float dx, dy, acceleration, speed;
    private boolean left, right, up;
    //A(LEFT), D(RIGHT); W(UP),S(DOWN)

    public Movement(float acceleration, float speed) {
        this.acceleration = acceleration;
        this.speed = speed;

    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
        Position position = entity.getComponent(Position.class);
        float x = position.getX();
        float y = position.getY();
        float radians = position.getRadians();
        float dt = gameData.getDeltaTime();

        if (up) {
            dx += cos(radians) * acceleration * dt;
            dy += sin(radians) * acceleration * dt;
        }

        float vec = (float) sqrt(dx * dx + dy * dy);
        if (vec > speed) {
            dx = (dx / vec) * speed;
            dy = (dy / vec) * speed;
        }

        // set position
        x += dx * dt;
        if (x > gameData.getSceneWidth()) {
            x = 0;
        } else if (x < 0) {
            x = gameData.getSceneWidth();
        }

        y += dy * dt;
        if (y > gameData.getSceneHeight()) {
            y = 0;
        } else if (y < 0) {
            y = gameData.getSceneHeight();
        }

        position.setX(x);
        position.setY(y);

        position.setRadians(radians);

    }

}
