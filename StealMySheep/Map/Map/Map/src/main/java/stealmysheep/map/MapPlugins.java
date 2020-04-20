/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.map;

import stealmysheep.common.assets.map.Tiletype;
import stealmysheep.common.assets.map.Tile;
import org.openide.util.lookup.ServiceProvider;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;

@ServiceProvider(service = IPlugin.class)
/*
 *
 * @author Antonio
 */

public class MapPlugins implements IPlugin {

    private static String currentMapName;
    private Tile[][] map;
    int[][] numberMap = {{7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
    {6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5}};

    @Override
    public void start(GameData gameData, World world) {
        // loadFromFile(currentMapName, gameData, world);
        Tiletype[][] tileMap = convertIntArrayToTileArray(this.numberMap);
        createMap(tileMap, gameData, world);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    private Tiletype[][] convertIntArrayToTileArray(int[][] mapArray) {
        Tiletype[][] tileMap = new Tiletype[mapArray.length][mapArray[0].length];
        for (int x = 0; x < mapArray.length; x++) {
            for (int y = 0; y < mapArray[x].length; y++) {
                tileMap[x][y] = Tiletype.values()[mapArray[x][y]];
            }
        }
        return tileMap;
    }

    private void createMap(Tiletype[][] tileTypeMap, GameData gameData, World world) {
        float tileHeight = 70;
        float tileWeight = 70;

        map = new Tile[tileTypeMap.length][tileTypeMap[0].length];

        for (int x = 0; x < tileTypeMap.length; x++) {
            for (int y = 0; y < tileTypeMap[x].length; y++) {
                Tile tile = new Tile(tileTypeMap[x][y].getImage());
                Position position = new Position(y * tileWeight, x * tileHeight, 0);
                tile.addComponent(position);
                world.addEntity(tile);
                map[x][y] = tile;
            }

        }
    }

}
