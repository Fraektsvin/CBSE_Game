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
            gameData.getInput().setKeyStatus(Input.UP, true);;
        }
        if (k == Keys.A) {
            gameData.getInput().setKeyStatus(Inputs.LEFT, true);
        }
        if (k == Keys.S) {
            gameData.getInput().setKeyStatus(Inputs.DOWN, true);
        }
        if (k == Keys.D) {
            gameData.getInput().setKeyStatus(Inputs.RIGHT, true);

        }
        if (k == Keys.R) {
            gameData.getInput().setKeyStatus(Inputs.R, false);
        }
        if (k == Buttons.LEFT) {
            gameData.getInput().setKeysStatus(Inputs.MOUSELEFT,false);
        }
       
        if (k == Keys.ENTER) {
            gameData.getInput().setKeyStatus(Input.ENTER, true);
        }
        if (k == Keys.ESCAPE) {
            gameData.getInput().setKeyStatus(Input.ESCAPE, true);
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
            gameData.getInput().setKeyStatus(Inputs.UP, false);
        }
        if (k == Keys.A) {
            gameData.getInput().setKeyStatus(Inputs.LEFT, false);
        }
        if (k == Keys.S) {
            gameData.getInput().setKeyStatus(Inputs.DOWN, false);
        }
        if (k == Keys.D) {
            gameData.getInput().setKeyStatus(Inputs.RIGHT, false);

        }
        if (k == Keys.R) {
            gameData.getInput().setKeyStatus(Inputs.R, false);
        }
         if (k == Buttons.LEFT) {
             gameData.getInput().setKeysStatus(Inputs.MOUSELEFT,false);
        }
        
        if (k == Keys.ENTER) {
            gameData.getInput().setKeyStatus(Input.ENTER, false);
        }
        if (k == Keys.ESCAPE) {
            gameData.getInput().setKeyStatus(Input.ESCAPE, false);
        }
        if (k == Keys.SPACE) {
            gameData.getInput().setKeyStatus(Input.SPACE, false);
        }
        if (k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT) {
            gameData.getInput().setKeyStatus(Input.SHIFT, false);
        }
        return true;
    }

}
