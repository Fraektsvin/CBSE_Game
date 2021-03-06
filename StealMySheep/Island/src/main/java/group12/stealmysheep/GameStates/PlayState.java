/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import group12.stealmysheep.Manager.AssetController;
import group12.stealmysheep.Manager.GameInputProcessor;
import group12.stealmysheep.Manager.WaveManager;
import group12.stealmysheep.island.Island;
import java.util.Collection;
import java.util.Stack;
import org.openide.util.Lookup;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.Health;
import stealmysheep.common.assets.map.Tile;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;
import stealmysheep.common.services.IPostUpdate;
import stealmysheep.common.services.IUpdate;
import stealmysheep.common.services.IWave;
import stealmysheep.common.game.Input;

/**
 *
 * @author oscar
 */
public class PlayState extends GameState {

    private SpriteBatch spriteBatch;
    private GameData gameData;
    private World world;
    private AssetController assetController;
    private WaveManager waveManager;
    private final Lookup lookup;
    private boolean paused;
    private Stack<GameState> gameStates;
    private Island island;

    public PlayState(Island island) {
        super(island);
        this.island = island;
        this.spriteBatch = island.getSpriteBatch();
        this.gameData = island.getGameData();
        this.world = island.getWorld();
        this.assetController = island.getAssetController();
        this.waveManager = new WaveManager();
        this.lookup = island.getLookup();
        this.paused = false;
        this.gameStates = island.getGameStates();

        for (IPlugin plugin : island.getResult().allInstances()) {
            plugin.start(gameData, world);
            island.getGamePlugins().add(plugin);
        }
        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render() {
        if (this.paused == false) {
            updateServices();
            wave();
            pause();
            draw();
            endGame();
        }
    }

    private void updateServices() {
        // Update
        for (IUpdate update : lookup.lookupAll(IUpdate.class)) {
            update.update(gameData, world);
        }

        // Post Update
        for (IPostUpdate postUpdate : lookup.lookupAll(IPostUpdate.class)) {
            postUpdate.postUpdate(gameData, world);
        }
    }

    private void draw() {
        spriteBatch.begin();

        for (Entity entity : world.getEntities(Tile.class)) {
            assetController.drawEntity(entity, this.spriteBatch);
        }

        for (Entity entity : world.getEntities()) {
            if (!entity.getClass().equals(Tile.class)) {
                assetController.drawEntity(entity, this.spriteBatch);

            }
        }

        spriteBatch.end();

        for (Entity entity : world.getEntities()) {
            if (entity.hasComponent(Health.class)) {
                assetController.drawHealth(entity);
            }
        }
    }

    private void wave() {
        Collection<? extends IWave> waves = lookup.lookupAll(IWave.class);

        boolean endWave = false;
        for (IWave wave : waves) {
            if (wave.endWave(gameData, world)) {
                endWave = true;
                break;
            }
        }

        if (endWave) {
            this.waveManager.setNextWave();

            for (IWave wave : waves) {
                wave.startWave(this.gameData, this.world, this.waveManager.getCurrentWave());
            }
        }
    }

    private void pause() {

        if (this.gameData.getInput().isDown(Input.ESCAPE)) {
//            this.gameData.getInput().setKeyStatus(Input.ESCAPE, false);
            this.gameData.getInput().resetKeys();
            this.paused = true;
            System.out.println("Pause Game");
            this.island.getGameStates().push(new Load(this.island));
        }
    }

    private void endGame() {
        if (this.gameData.isEndGame()) {
            System.out.println("Game ending");
            this.gameData.getInput().resetKeys();
            this.gameData.setEndGame(false);
            for (IPlugin plugin : island.getGamePlugins()) {
                plugin.stop(island.getGameData(), island.getWorld());
            }

            island.getGameStates().pop();
            island.getGameStates().push(new GameOver(island));
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
