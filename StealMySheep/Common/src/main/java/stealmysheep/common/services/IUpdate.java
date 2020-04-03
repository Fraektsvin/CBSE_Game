/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.services;

import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;

/**
 *
 * @author oscar
 */
public interface IUpdate {

    public void update(GameData gameData, World world);

}
