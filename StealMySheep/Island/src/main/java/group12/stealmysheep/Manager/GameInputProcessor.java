/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.Manager;

/**
 *
 * @author Antonio
 */
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import stealmysheep.common.game.Input;

public class GameInputProcessor extends InputAdapter {

    private Input inputs;

    public boolean keyDown(int k) {
        if (k == Keys.W) {
            inputs.setKeyStatus(Input.UP, true);;
        }
        if (k == Keys.A) {
            inputs.setKeyStatus(inputs.LEFT, true);
        }
        if (k == Keys.S) {
            inputs.setKeyStatus(inputs.DOWN, true);
        }
        if (k == Keys.D) {
            inputs.setKeyStatus(inputs.RIGHT, true);
        }
        if (k == Keys.R) {
            inputs.setKeyStatus(inputs.R, false);
        }
        if (k == Buttons.LEFT) {
            inputs.setKeysStatus(Inputs.MOUSELEFT,false);
        }
       
        if (k == Keys.ENTER) {
            inputs.setKeyStatus(inputs.ENTER, true);
        }
        if (k == Keys.ESCAPE) {
            inputs.setKeyStatus(inputs.ESCAPE, true);
        }
        if (k == Keys.SPACE) {
            inputs.setKeyStatus(inputs.SPACE, true);
        }
        if (k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
            inputs.setKeyStatus(inputs.SHIFT, true);
        }
        return true;
    }

    public boolean keyUp(int k) {
        if (k == Keys.W) {
            inputs.setKeyStatus(inputs.UP, false);
        }
        if (k == Keys.A) {
            inputs.setKeyStatus(inputs.LEFT, false);
        }
        if (k == Keys.S) {
            inputs.setKeyStatus(inputs.DOWN, false);
        }
        if (k == Keys.D) {
            inputs.setKeyStatus(inputs.RIGHT, false);
        }
        if (k == Keys.R) {
            inputs.setKeyStatus(inputs.R, false);
        }
         if (k == Buttons.LEFT) {
            inputs.setKeysStatus(Inputs.MOUSELEFT,false);
        }
        
        if (k == Keys.ENTER) {
            inputs.setKeyStatus(inputs.ENTER, false);
        }
        if (k == Keys.ESCAPE) {
            inputs.setKeyStatus(inputs.ESCAPE, false);
        }
        if (k == Keys.SPACE) {
            inputs.setKeyStatus(inputs.SPACE, false);
        }
        if (k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
            inputs.setKeyStatus(inputs.SHIFT, false);
        }
        return true;
    }

}
