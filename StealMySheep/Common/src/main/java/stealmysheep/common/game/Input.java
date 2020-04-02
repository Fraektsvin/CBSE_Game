/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.game;

import com.badlogic.gdx.Gdx;

/**
 *
 * @author oscar
 */
public class Input {

    private boolean[] keys, pressedKeys;
    private static final int NUMPADKEYS = 9;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ENTER = 4;
    public static final int ESCAPE = 5;
    public static final int SPACE = 6;
    public static final int SHIFT = 7;
    public static final int R = 8;

    public static float mouseX;
    public static float mouseY;

    public void update() {
        for (int i = 0; i < NUMPADKEYS; i++) {
            pressedKeys[i] = keys[i];
        }

        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
    }

    public void setKeyStatus(int key, boolean pressed) {
        keys[key] = pressed;
    }

    public boolean isDown(int key) {
        return keys[key];
    }

    public boolean isPressed(int key) {
        return keys[key] && !pressedKeys[key];
    }

}
