/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import group12.stealmysheep.island.Island;
import stealmysheep.common.services.IPlugin;

/**
 *
 * @author Naimo
 */
public class GameOver extends GameState {

    private Stage stage;
    private Table menuTable;

    private Texture resetTexture;
    private Texture menuTexture;

    private TextureRegion resetTextureRegion;
    private TextureRegion menuTextureRegion;

    private TextureRegionDrawable resetTexRegionDrawable;
    private TextureRegionDrawable menuTexRegionDrawable;

    private ImageButton resetButton;
    private ImageButton menuButton;

    private SpriteBatch batch;
    private Texture imageGameOver;

    public GameOver(Island island) {
        super(island);
        stage = new Stage(new ScreenViewport());

        batch = new SpriteBatch();
        imageGameOver = new Texture("skin/GameOverLogo.PNG");

        menuTable = new Table();

        resetTexture = new Texture(Gdx.files.internal("skin/resetButton.png"));
        resetTextureRegion = new TextureRegion(resetTexture);
        resetTexRegionDrawable = new TextureRegionDrawable(resetTextureRegion);
        resetButton = new ImageButton(resetTexRegionDrawable);
        resetButton.setSize(180, 54);
        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Reset game button clicked");
                while (!island.getGameStates().isEmpty()) {
                    island.getGameStates().pop();
                }

                for (IPlugin plugin : island.getGamePlugins()) {
                    plugin.stop(island.getGameData(), island.getWorld());
                }
                island.getGameStates().push(new PlayState(island));

            }
        });
        menuTable.add(resetButton);
        menuTable.row().space(50);
        //stage.addActor(startButton);

        menuTexture = new Texture(Gdx.files.internal("skin/menuButton.png"));
        menuTextureRegion = new TextureRegion(menuTexture);
        menuTexRegionDrawable = new TextureRegionDrawable(menuTextureRegion);
        menuButton = new ImageButton(menuTexRegionDrawable);
        //settingButton.setSize(300, 54);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu button clicked");
                while (!island.getGameStates().isEmpty()) {
                    island.getGameStates().pop();
                }

                for (IPlugin plugin : island.getGamePlugins()) {
                    plugin.stop(island.getGameData(), island.getWorld());
                }
                island.getGameStates().push(new GameMenu(island));
                dispose();
            }
        });
        menuTable.add(menuButton);
        menuTable.row();
        //stage.addActor(settingButton);

        menuTable.setFillParent(true);
        //menuTable.debug();
        stage.addActor(menuTable);
        //stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        batch.draw(imageGameOver, 200, 600);
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
