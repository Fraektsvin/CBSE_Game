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
public interface IPostUpdate {

    public void postUpdate(GameData gameData, World world);
}
