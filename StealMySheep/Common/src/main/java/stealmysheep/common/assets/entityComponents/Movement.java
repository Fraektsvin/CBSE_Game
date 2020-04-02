/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.entityComponents;

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
        float radians = position.getRadians();
        float dt = gameData.getDeltaTime();

        if (up) {
            dx = x;
            dy += acceleration * dt;
        }
        if (down) {
            dx = x;
            dy -= acceleration * dt;

        }
        if (left) {
            dx += acceleration * dt;
            dy = y;
        }
        if (right) {
            dx -= acceleration * dt;
            dy = y;
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
