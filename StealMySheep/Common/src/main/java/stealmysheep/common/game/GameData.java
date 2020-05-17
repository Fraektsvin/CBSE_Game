/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.game;

/**
 *
 * @author oscar
 */
public class GameData {

    private float deltaTime;
    private int sceneHeight, sceneWidth;
    private Input input;
    private final Input keys = new Input();
    private boolean endGame;

    public GameData() {
        this.input = new Input();
        this.endGame = false;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }

    public int getSceneHeight() {
        return sceneHeight;
    }

    public void setSceneHeight(int sceneHeight) {
        this.sceneHeight = sceneHeight;
    }

    public int getSceneWidth() {
        return sceneWidth;
    }

    public void setSceneWidth(int sceneWidth) {
        this.sceneWidth = sceneWidth;
    }

    public Input getInput() {
        return input;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

}
