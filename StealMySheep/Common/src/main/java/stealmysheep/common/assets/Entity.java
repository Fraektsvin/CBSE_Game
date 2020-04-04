/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.assets;

import stealmysheep.common.assets.entityComponents.Component;

import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author oscar
 */
public abstract class Entity {

    private UUID id;
    private HashMap<Class<? extends Component>, Component> components;

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

    public <C extends Component> void addComponent(Component component) {
        this.components.put(component.getClass(), component);
    }

    public <C extends Component>void removeComponent(Class<C> componentClass) {
        this.components.remove(componentClass);
    }

    public <C extends Component> C getComponent(Class<C> componentClass) {
        //noinspection unchecked
        return (C) this.components.get(componentClass);
    }

    public <C extends Component> boolean hasComponent(Class<C> componentClass) {
        return this.components.containsKey(componentClass);
    }

}
