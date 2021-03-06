/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.Manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.Health;
import stealmysheep.common.assets.entityComponents.Position;

/**
 *
 * @author oscar
 */
public class AssetController {

    public final AssetManager assetManager;
    private ShapeRenderer sr;

    public AssetController() {
        assetManager = new AssetManager();
        loadAssets();
        assetManager.finishLoading();
        this.sr = new ShapeRenderer();
    }

    private void loadAssets() {
        assetManager.load("assets/player.png", Texture.class);
        assetManager.load("assets/thief.png", Texture.class);
        assetManager.load("assets/sheep.png", Texture.class);
        assetManager.load("assets/rock.png", Texture.class);
        assetManager.load("assets/waterBottom.png", Texture.class);
        assetManager.load("assets/waterTop.png", Texture.class);
        assetManager.load("assets/waterRight.png", Texture.class);
        assetManager.load("assets/waterLeft.png", Texture.class);
        assetManager.load("assets/waterTopLeft.png", Texture.class);
        assetManager.load("assets/waterTopRight.png", Texture.class);
        assetManager.load("assets/waterBottomRight.png", Texture.class);
        assetManager.load("assets/waterBottomLeft.png", Texture.class);
        assetManager.load("assets/sand.png", Texture.class);
        assetManager.load("assets/projectile.png", Texture.class);
        assetManager.load("assets/archer.png", Texture.class);
        assetManager.load("assets/tileRock.png", Texture.class);
        assetManager.load("assets/tileBush.png", Texture.class);
    }

    public Texture getAsset(String name) {
        return assetManager.get(name, Texture.class);
    }

    public void drawEntity(Entity entity, SpriteBatch spriteBatch) {
        if (entity.getImage() != null) {
            Texture texture = this.assetManager.get("assets/" + entity.getImage());
            Sprite sprite = new Sprite(texture);
            Position position = entity.getComponent(Position.class);

            sprite.setScale(0.35f);
            if (position.getRadians() > Math.PI / 2 || position.getRadians() < -(Math.PI / 2)) {
                sprite.flip(true, false);
            }

            float x = position.getX() - sprite.getWidth() / 2;
            float y = position.getY() - sprite.getHeight() / 2;
            sprite.setPosition(x, y);
            sprite.draw(spriteBatch);

        }
    }

    public void drawHealth(Entity entity) {
        Health health = entity.getComponent(Health.class);
        Position position = entity.getComponent(Position.class);
        Texture texture = this.assetManager.get("assets/" + entity.getImage());

        float totalBarWidth = 50;
        float width = (float) health.getHealth() / (float) health.getMaxHealth() * totalBarWidth;
        this.sr.begin(ShapeType.Filled);
        this.sr.setColor(Color.GREEN);
        this.sr.rect(position.getX() - 50 / 2, position.getY() + texture.getHeight() * 0.35f / 2 + 15, width, 5);
        this.sr.end();

    }

}
