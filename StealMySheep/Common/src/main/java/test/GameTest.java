/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import stealmysheep.common.assets.Player;
import stealmysheep.common.assets.entityComponents.Position;

/**
 *
 * @author oscar
 */
public class GameTest implements ApplicationListener {
    
    private World world = new World();
    private final GameData gameData = new GameData();
    private static OrthographicCamera cam;
    private List<IPlugin> gamePlugins = new CopyOnWriteArrayList<>();
    private SpriteBatch spriteBatch;
    private Player player;
    
    @Override
    public void create() {
        gameData.setSceneWidth(Gdx.graphics.getWidth());
        gameData.setSceneHeight(Gdx.graphics.getHeight());
        
        cam = new OrthographicCamera(gameData.getSceneWidth(), gameData.getSceneHeight());
        cam.translate(gameData.getSceneWidth() / 2, gameData.getSceneHeight() / 2);
        cam.update();
        spriteBatch = new SpriteBatch();
        String path = "\\OneDrive\\Skrivebord\\Project\\Steal My Sheep\\StealMySheep\\Common\\images\\player.PNG";
        player = new Player(path);
        player.addComponent(new Position(gameData.getSceneHeight() / 2, gameData.getSceneWidth() / 2, 3.1415f / 2));
    }
    
    @Override
    public void resize(int i, int i1) {
    }
    
    @Override
    public void render() {
        spriteBatch.begin();
        player.render(spriteBatch);
        spriteBatch.end();
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
    
    @Override
    public void dispose() {
    }
    
}
