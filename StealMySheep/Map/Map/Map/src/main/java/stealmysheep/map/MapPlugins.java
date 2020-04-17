/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.lookup.ServiceProvider;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;
@ServiceProvider(service = IPlugin.class)
/**
 *
 * @author Antonio
 */
/**
 *
 * @author Antonio
 */

public class MapPlugins implements IPlugin, MapApi {

    private static String currentMapName;
    private Tile[][] map;
    int[][] Numbermap = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    @Override
    public void start(GameData gameData, World world) {
        // loadFromFile(currentMapName, gameData, world);
        convertIntArrayToTileArray(Numbermap, gameData, world);
    }

    private void convertIntArrayToTileArray(int[][] mapArray, GameData gameData, World world) {
        Tiletype[][] tmpTiles = new Tiletype[mapArray.length][mapArray[0].length];
        for (int x = 0; x < mapArray.length; x++) {
            for (int y = 0; y < mapArray[x].length; y++) {
                tmpTiles[x][y] = Tiletype.values()[mapArray[x][y]];
            }
        }
        createMap(tmpTiles, gameData, world);
    }

    @Override
    public void createMap(Tiletype[][] d, GameData gameData, World world) {
        float tileHeight = 70;
        float tileWeight = 70;

        map = new Tile[d.length][d[0].length];

        for (int r = 0; r < d.length; r++) {
            for (int c = 0; c < d[r].length; c++) {
                Tile t = new Tile(d[r][c].getImage());
                Position p = new Position(c * tileWeight, r * tileHeight, 0);
                t.addComponent(p);
                map[r][c] = t;
            }

        }
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
/* @Override
    public void loadFromFile(String File, GameData gameData, World world) {
         try {
            InputStream is;
            if (File.equals("DefaultMap.txt")) {
                is = getClass().getClassLoader().getResourceAsStream("/maps/" + MapPlugins.currentMapName);
            } else {
                String path = System.getProperty("user.home") + "/racing_game/maps/";
                String file = path + File;

                is = new FileInputStream(file);
            }
            ArrayList<int[]> data = new ArrayList<>();
            try (Scanner sc = new Scanner(is)) {
                while (sc.hasNextLine()) {
                    String nextLine = sc.nextLine();
                    String[] values = nextLine.split(",");
                    int[] row = new int[values.length];
                    for (int i = 0; i < values.length; i++) {
                        row[i] = Integer.parseInt(values[i]);
                    }
                    data.add(row);
                }
                int[][] map = new int[data.size()][data.get(0).length];
                for (int i = 0; i < data.size(); i++) {
                    map[i] = data.get(i);
                }
                createMap(map, gameData, world);
                currentMapName = File;
            }
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapPlugins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapPlugins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

*/
