/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import game.GameData;
import game.World;

/**
 *
 * @author oscar
 */
public interface IUpdate {

    public void update(GameData gameData, World world);
}
