/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.island;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author Naimo
 */
public class Load extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;
    private Texture imageSetting;
    private Table mainTable;

    private CheckBox aI;
    private CheckBox player;
    private CheckBox enemy;
    private CheckBox sheep;

    private Texture menuTexture;
    private TextureRegion menuTextureRegion;
    private TextureRegionDrawable menuTexRegionDrawable;
    private ImageButton menuButton;
    
    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        skin();
        batch = new SpriteBatch();
        imageSetting = new Texture("skin/settingLogo.PNG");
        mainTable = new Table();
        aI = new CheckBox("AI", skin);
        aI.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(aI.isChecked());
                System.out.println("AI has been unloaded");
            }
        });
        mainTable.add(aI);
        mainTable.row().space(1);

        player = new CheckBox("Player", skin);
        player.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(player.isChecked());
                System.out.println("Player has been unloaded");
            }
        });
        mainTable.add(player);
        mainTable.row();

        enemy = new CheckBox("Enemy", skin);
        enemy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(enemy.isChecked());
                System.out.println("Enemy has been unloaded");
            }
        });
        mainTable.add(enemy);
        mainTable.row();

        sheep = new CheckBox("Sheep", skin);
        sheep.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(sheep.isChecked());
                System.out.println("Sheep has been unloaded");
            }
        });
        mainTable.add(sheep);
        mainTable.row();
          
        menuTexture = new Texture(Gdx.files.internal("skin/menuButton3.png"));
        menuTextureRegion = new TextureRegion(menuTexture);
        menuTexRegionDrawable = new TextureRegionDrawable(menuTextureRegion);
        menuButton = new ImageButton(menuTexRegionDrawable);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu button clicked");
            }
        });
        mainTable.add(menuButton);
        mainTable.row();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);
        Gdx.input.setInputProcessor(stage);
    }

    public void skin() {
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 3, (int) Gdx.graphics.getHeight() / 13, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));
        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.up = skin.newDrawable("background", Color.WHITE);
        checkBoxStyle.fontColor = Color.DARK_GRAY;
        checkBoxStyle.font = skin.getFont("default");
        skin.add("default", checkBoxStyle);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        batch.draw(imageSetting, 50, 590);
        batch.end();
    }
    
    @Override
    public void dispose(){
        stage.dispose();
    }
}
