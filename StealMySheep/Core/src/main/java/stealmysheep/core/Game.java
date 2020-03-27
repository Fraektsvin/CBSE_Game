/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.core;

import assets.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.GameData;
import game.World;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import managers.GameInputProcessor;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import services.IPlugin;
import services.IPostUpdate;
import services.IUpdate;

/**
 *
 * @author nadinfariss
 */
public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private List<IPostUpdate> entityIPostUpdate = new ArrayList<>();
    private List<IUpdate> entityIUpdate = new ArrayList<>();
    private List<IPlugin> entityPlugins = new ArrayList<>();
    private World world = new World();
    private Lookup.Result<IPlugin> result;

    @Override
    public void create() {

        gameData.setSceneWidth(Gdx.graphics.getWidth());
        gameData.setSceneHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getSceneWidth(), gameData.getSceneHeight());
        cam.translate(gameData.getSceneWidth() / 2, gameData.getSceneHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        result = lookup.lookupResult(IPlugin.class);
        result.addLookupListener(lookupListener);
        result.allItems();

        for (IPlugin plugin : result.allInstances()) {
            plugin.start(gameData, world);
            entityPlugins.add(plugin);

        }
    }

    // everytime you make a new service it has to be implemented her for the serviceloader to regernize a class that use that service (interface)
    private Collection<? extends IPlugin> getPluginServices() {
        return lookup.lookupAll(IPlugin.class);
    }

    private Collection<? extends IUpdate> getEntityProcessingServices() {
        return lookup.lookupAll(IUpdate.class);
    }

    private Collection<? extends IPostUpdate> getPostEntityProcessingServices() {
        return lookup.lookupAll(IPostUpdate.class);
    }

    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IPlugin> updated = result.allInstances();

            for (IPlugin igps : updated) {
                if (!entityPlugins.contains(igps)) {
                    igps.start(gameData, world);
                    entityPlugins.add(igps);
                }
            }

            //remove module
            for (IPlugin igps : entityPlugins) {
                if (!updated.contains(igps)) {
                    igps.stop(gameData, world);
                    entityPlugins.remove(igps);
                }
            }
        }

    };

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDeltaTime(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for (IUpdate entityProcessingService : getEntityProcessingServices()) {
            entityProcessingService.update(gameData, world);
        }
        for (IPostUpdate entityProcessorService : getPostEntityProcessingServices()) {
            entityProcessorService.update(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {

            sr.setColor(1, 1, 1, 1);

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
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

}
