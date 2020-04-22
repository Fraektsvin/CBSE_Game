/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.Manager;

import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.map.Tile;
import stealmysheep.common.game.World;



/**
 *
 * @author Antonio
 */
public class SpawnManager {
    
    private int currentWave; 

    public int getCurrentWave() {
        return currentWave;
    }

    public SpawnManager() {
        this.currentWave = 0;
    }

    public void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }
    
    public boolean EndWaveCheck(World world) {
       return world.getEntities(Enemy.class).isEmpty();

    }
    
}
