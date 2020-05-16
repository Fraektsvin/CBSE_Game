/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.GameStates;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 *
 * @author Naimo
 */
public class GameWin extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;
    private Table mainTable;
    private SpriteBatch batch;
    private Texture imageWin;
       
    private Label healthLabel;
    private Label sheepLabel;
    private Label pointsLabel;
    
    private Texture resetTexture;
    private Texture menuTexture;

    private TextureRegion resetTextureRegion;
    private TextureRegion menuTextureRegion;

    private TextureRegionDrawable resetTexRegionDrawable;
    private TextureRegionDrawable menuTexRegionDrawable;

    private ImageButton resetButton;
    private ImageButton menuButton;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        imageWin = new Texture("skin/winLogo.PNG");
        skin();
        mainTable = new Table();
        
        healthLabel = new Label("Player Health: ", skin);
        mainTable.add(healthLabel);
        mainTable.row().space(10);
        
        sheepLabel = new Label("Amount Of Sheep Left: ", skin);
        mainTable.add(sheepLabel);
        mainTable.row();
        
        pointsLabel = new Label("Total Points Gain: ", skin);
        mainTable.add(pointsLabel);
        mainTable.row().space(10);
        
        resetTexture = new Texture(Gdx.files.internal("skin/resetButton2.png"));
        resetTextureRegion = new TextureRegion(resetTexture);
        resetTexRegionDrawable = new TextureRegionDrawable(resetTextureRegion);
        resetButton = new ImageButton(resetTexRegionDrawable);
        resetButton.setSize(180, 54);
        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Reset game button clicked");
            }
        });
        mainTable.add(resetButton);
        mainTable.row().space(50);
        //stage.addActor(startButton);

        menuTexture = new Texture(Gdx.files.internal("skin/menuButton2.png"));
        menuTextureRegion = new TextureRegion(menuTexture);
        menuTexRegionDrawable = new TextureRegionDrawable(menuTextureRegion);
        menuButton = new ImageButton(menuTexRegionDrawable);
        //settingButton.setSize(300, 54);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Menu button clicked");
            }
        });
        mainTable.add(menuButton);
        mainTable.row();
        //stage.addActor(settingButton);
        
        mainTable.setFillParent(true);
        //menuTable.debug();
        stage.addActor(mainTable);
        //stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);
    }
    
    public void skin(){
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);
        
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 3, (int) Gdx.graphics.getHeight() / 13, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
 
        pixmap.fill();
        skin.add("background", new Texture(pixmap));
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("background", Color.WHITE);
        labelStyle.fontColor = Color.CHARTREUSE;
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);
    }
    
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        batch.draw(imageWin, 180, 590);
        batch.end();
    }
    
    @Override
    public void dispose(){
        stage.dispose();
    }
}
