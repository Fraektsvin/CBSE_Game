/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group12.stealmysheep.common.assets;

import java.util.HashMap;
import java.util.UUID;
import group12.stealmysheep.common.assets.entityComponents.Component;

/**
 *
 * @author oscar
 */
public class Entity {

    private UUID id = UUID.randomUUID();
    private HashMap<Class, Component> components;

    public Entity() {
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

}
