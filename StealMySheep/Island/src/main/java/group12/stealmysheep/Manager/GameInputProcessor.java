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
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.Input;

public class GameInputProcessor extends InputAdapter {

    private final GameData gameData;

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean keyDown(int k) {

        if (k == Keys.W) {
            gameData.setKeyStatus(input.UP, true);;
        }
        if (k == Keys.A) {
            gameData.setKeyStatus(inputs.LEFT, true);
        }
        if (k == Keys.S) {
            gameData.setKeyStatus(inputs.DOWN, true);
        }
        if (k == Keys.D) {
            gameData.setKeyStatus(inputs.RIGHT, true);

        }
        if (k == Keys.R) {
            gameData.setKeyStatus(inputs.R, false);
        }
        if (k == Buttons.LEFT) {
            gameData.setKeysStatus(inputs.MOUSELEFT,false);
        }
       
        if (k == Keys.ENTER) {
            gameData.getInput().setKeyStatus(input.ENTER, true);
        }
        if (k == Keys.ESCAPE) {
            gameData.getInput().setKeyStatus(input.ESCAPE, true);
        }
        if (k == Keys.SPACE) {
            gameData.getInput().setKeyStatus(Input.SPACE, true);
        }
        if (k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
            gameData.getInput().setKeyStatus(input.SHIFT, true);
        }
        return true;
    }

    public boolean keyUp(int k) {

        if (k == Keys.W) {
            gameData.setKeyStatus(inputs.UP, false);
        }
        if (k == Keys.A) {
            gameData.setKeyStatus(inputs.LEFT, false);
        }
        if (k == Keys.S) {
            gameData.setKeyStatus(inputs.DOWN, false);
        }
        if (k == Keys.D) {
            gameData.setKeyStatus(inputs.RIGHT, false);

        }
        if (k == Keys.R) {
            gameData.setKeyStatus(inputs.R, false);
        }
         if (k == Buttons.LEFT) {
             gameData.setKeysStatus(inputs.MOUSELEFT,false);
        }
        
        if (k == Keys.ENTER) {
            gameData.getInput().setKeyStatus(input.ENTER, false);
        }
        if (k == Keys.ESCAPE) {
            gameData.getInput().setKeyStatus(input.ESCAPE, false);
        }
        if (k == Keys.SPACE) {
            gameData.getInput().setKeyStatus(input.SPACE, false);
        }
        if (k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
            gameData.getInput().setKeyStatus(input.SHIFT, false);
        }
        return true;
    }

}
