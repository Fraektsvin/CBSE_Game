/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets.entityComponents;

import stealmysheep.common.assets.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import stealmysheep.common.game.GameData;

/**
 *
 * @author oscar
 */
public class Drawing implements Component {

    private Sprite sprite;
    private Texture texture;

    public Drawing(String imagePath) {
        this.texture = new Texture(Gdx.files.internal(imagePath));
        this.sprite = new Sprite(this.texture);
    }

    public void setRotation(float rotation) {
        this.sprite.setRotation(rotation);
    }

    public void flipVertical() {
        this.sprite.flip(false, true);
    }

    public void setScale(float scaleX, float scaleY) {
        this.sprite.setScale(scaleX, scaleY);
    }

    public void setSize(float width, float height) {
        this.sprite.setSize(width, height);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void update(Entity entity, GameData gameData) {

        Position position = entity.getComponent(Position.class);
        if (position != null) {
            float x = position.getX();
            float y = position.getY();

            this.sprite.setPosition(x, y);
        }

    }

}
