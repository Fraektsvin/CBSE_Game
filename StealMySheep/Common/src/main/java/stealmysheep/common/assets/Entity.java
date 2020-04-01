/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;
import stealmysheep.common.assets.entityComponents.Component;
import stealmysheep.common.assets.entityComponents.Position;

/**
 *
 * @author oscar
 */
public class Entity {

    private UUID id;
    private HashMap<Class, Component> components;

    private Texture texture;

    public Entity() {
        this.id = UUID.randomUUID();
        this.components = new HashMap<>();
    }

    public Entity(String imagePath) {
        this();

        String path = Paths.get("").toAbsolutePath().toString();
        String newpath = path.substring(0, path.length() - 32);
        this.texture = new Texture(Gdx.files.absolute(newpath + "\\Common\\src\\images\\" + imagePath));

    }

    public String getId() {
        return id.toString();
    }

    public void addComponent(Component component) {
        this.components.put(component.getClass(), component);
    }

    public void removeComponent(Class componentClass) {
        if (this.components.containsKey(componentClass)) {
            this.components.remove(componentClass);
        }
    }

    public <O extends Component> O getComponent(Class componentClass) {
        return (O) this.components.get(componentClass);
    }

    public boolean hasComponent(Class componentClass) {
        return this.components.containsKey(componentClass);
    }

    public void render(SpriteBatch batch) {

        Position position = getComponent(Position.class);
        if (position != null && this.texture != null) {
            float x = position.getX();
            float y = position.getY();
            float radians = position.getRadians();

            float radiansTop = 3.141592f / 2;
            float radiansBottom = (3 * 3.141592f) / 2;

            boolean flip = false;
            if (radiansTop < radians && radians < radiansBottom) {
                flip = true;
                x = x + this.texture.getWidth() / 2;
                y = y - this.texture.getHeight() / 2;
            } else {
                x = x - this.texture.getWidth() / 2;
                y = y - this.texture.getHeight() / 2;
            }

            batch.draw(this.texture, x, y, flip ? -this.texture.getWidth() : this.texture.getWidth(), this.texture.getHeight());
        }

    }

}
