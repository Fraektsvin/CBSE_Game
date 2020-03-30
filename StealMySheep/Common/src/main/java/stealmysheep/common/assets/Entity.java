/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;
import java.util.UUID;
import stealmysheep.common.assets.entityComponents.Component;
import stealmysheep.common.assets.entityComponents.Position;

/**
 *
 * @author oscar
 */
public class Entity {
    
    private AssetManager assetManager;
    
    private UUID id = UUID.randomUUID();
    private HashMap<Class, Component> components;
    
    private Texture texture;
    
    public Entity() {
        this.components = new HashMap<>();
    }
    
    public Entity(String imagePath) {
        this.components = new HashMap<>();
        this.assetManager = new AssetManager();
        this.texture = new Texture(Gdx.files.internal("./bin/player.png"));

        //Gdx.files.internal(imagePath);
        //this.texture = assetManager.get(imagePath, Texture.class);
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
        if (position != null) {
            float x = position.getX() - texture.getWidth() / 2;
            float y = position.getY() - texture.getHeight() / 2;
            
            float radiansTop = (float) (Math.PI / 2);
            float radiansBottom = (float) ((3 * Math.PI) / 2);
            
            boolean flip = false;
            if (radiansTop < radiansBottom) {
                flip = true;
            }
            
            batch.draw(this.texture, flip ? x + this.texture.getWidth() : x, y, flip ? -this.texture.getWidth() : this.texture.getWidth(), this.texture.getHeight());
            
        }
        
    }
    
}
