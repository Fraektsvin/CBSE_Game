/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.island;

/**
 *
 * @author Antonio
 */
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import group12.stealmysheep.GameStates.GameMenu;
import group12.stealmysheep.GameStates.GameState;
import group12.stealmysheep.Manager.AssetController;
import group12.stealmysheep.Manager.GameInputProcessor;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;

public class Island implements ApplicationListener {

    public static OrthographicCamera cam;

    private final GameData gameData = new GameData();
    private World world = new World();
    private SpriteBatch spriteBatch;
    private AssetController assetController;

    private final Lookup lookup = Lookup.getDefault();
    private List<IPlugin> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IPlugin> result;

    private Stack<GameState> gameStates;

    @Override
    public void create() {
        this.gameStates = new Stack<>();
        spriteBatch = new SpriteBatch();
        assetController = new AssetController();

        gameData.setSceneWidth(Gdx.graphics.getWidth());
        gameData.setSceneHeight(Gdx.graphics.getHeight());
        System.out.println(gameData.getSceneHeight());

        cam = new OrthographicCamera(gameData.getSceneWidth(), gameData.getSceneHeight());
        cam.translate(gameData.getSceneWidth() / 2, gameData.getSceneHeight() / 2);
        cam.update();

        gameStates.push(new GameMenu(this));

        result = lookup.lookupResult(IPlugin.class);
        result.addLookupListener(lookupListener);
        result.allItems();

    }

    @Override
    public void render() {
        gameData.setDeltaTime(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameData.getInput().update();
        gameData.getInput().updateMouse(Gdx.input.getX(), gameData.getSceneHeight() - Gdx.input.getY());

        this.gameStates.peek().render();
    }

    @Override
    public void resize(int width, int height) {
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

    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IPlugin> updated = result.allInstances();

            for (IPlugin plugins : updated) {
                // Newly installed modules
                if (!gamePlugins.contains(plugins)) {
                    plugins.start(gameData, world);
                    gamePlugins.add(plugins);
                }
            }

            // Stop and remove module
            for (IPlugin plugins : gamePlugins) {
                if (!updated.contains(plugins)) {
                    plugins.stop(gameData, world);
                    gamePlugins.remove(plugins);
                }
            }
        }

    };

    public GameData getGameData() {
        return gameData;
    }

    public World getWorld() {
        return world;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public AssetController getAssetController() {
        return assetController;
    }

    public List<IPlugin> getGamePlugins() {
        return gamePlugins;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public Lookup.Result<IPlugin> getResult() {
        return result;
    }

    public LookupListener getLookupListener() {
        return lookupListener;
    }

    public Stack<GameState> getGameStates() {
        return gameStates;
    }

}
