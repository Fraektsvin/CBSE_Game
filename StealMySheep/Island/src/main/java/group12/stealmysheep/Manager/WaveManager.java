/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.Manager;

import stealmysheep.common.assets.Enemy;
import stealmysheep.common.game.World;

/**
 *
 * @author Antonio
 */
public class WaveManager {

    private int currentWave;

    public WaveManager() {
        this.currentWave = 0;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    public void setNextWave() {
        currentWave += 1;
    }

}
