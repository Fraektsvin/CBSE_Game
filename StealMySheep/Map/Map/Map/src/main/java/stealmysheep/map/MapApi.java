/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.map;

import stealmysheep.common.assets.map.Tiletype;
import javax.swing.text.html.parser.Entity;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;

/**
 *
 * @author Antonio
 */
interface MapApi {

    void createMap(Tiletype[][] d, GameData gameData, World world);
}

