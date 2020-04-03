/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

import java.util.HashMap;
import java.util.UUID;
import stealmysheep.common.assets.entityComponents.Component;

/**
 *
 * @author oscar
 */
public class Entity {

    private UUID id;
    private HashMap<Class, Component> components;

    private String image;

    public Entity() {
        this.id = UUID.randomUUID();
        this.components = new HashMap<>();
    }

    public Entity(String image) {
        this();
        this.image = image;
    }

    public String getId() {
        return id.toString();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

}
