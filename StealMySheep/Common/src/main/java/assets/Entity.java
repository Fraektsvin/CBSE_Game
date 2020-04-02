/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import assets.entityComponents.Component;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author oscar
 */
public class Entity {

    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
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

    public void addComponent(float deacceleration, float acceleration, float maxSpeed, float rotationSpeed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

}
