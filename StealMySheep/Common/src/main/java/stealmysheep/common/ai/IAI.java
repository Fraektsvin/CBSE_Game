/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.ai;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;

/**
 *
 * @author NidaBasaran
 */
public interface IAI {

    public void moveEntity(Entity entity, Node goal, World world, GameData gameData);

}