/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.GameStates;

import group12.stealmysheep.island.Island;

/**
 *
 * @author oscar
 */
public abstract class GameState {

    private Island island;

    public GameState(Island island) {
        this.island = island;
    }

    public abstract void dispose();

    public abstract void render();

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
    }

}
