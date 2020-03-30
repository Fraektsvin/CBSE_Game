/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.common.assets.entityComponents;

import group12.stealmysheep.common.assets.Entity;
import group12.stealmysheep.common.game.GameData;

/**
 *
 * @author oscar
 */
public interface Component {

    void update(Entity entity, GameData gameData);
}
