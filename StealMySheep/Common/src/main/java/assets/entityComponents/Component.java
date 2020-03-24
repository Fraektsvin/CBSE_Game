/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.entityComponents;

import assets.Entity;
import game.GameData;

/**
 *
 * @author oscar
 */
public interface Component {

    void update(Entity entity, GameData gameData);
}
