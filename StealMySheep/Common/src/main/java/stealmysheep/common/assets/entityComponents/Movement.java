/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.entityComponents;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.GameData;

/**
 *
 * @author oscar
 */
public class Movement implements Component {

    private float dx, dy, acceleration, speed;
    private boolean left, right, up, down;
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

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    @Override
    public void update(Entity entity, GameData gameData) {
        Position position = entity.getComponent(Position.class);
        float x = position.getX();
        float y = position.getY();
        float dt = gameData.getDeltaTime();

        if (up) {
            dy += acceleration * dt;
        } else if (down) {
            dy -= acceleration * dt;
        }
        if (left) {
            dx -= acceleration * dt;
        } else if (right) {
            dx += acceleration * dt;
        }

        float vec = (float) sqrt(dx * dx + dy * dy);
        if (down == false && up == false && vec > 0) {
            dy -= (dy / vec) * 600 * dt;
        }

        if (left == false && right == false && vec > 0) {
            dx -= (dx / vec) * 600 * dt;
        }

        if (vec > speed) {
            dx = (dx / vec) * speed;
            dy = (dy / vec) * speed;
        }
        // set position
        x += dx * dt;
        y += dy * dt;

        position.setX(x);
        position.setY(y);

    }

}
