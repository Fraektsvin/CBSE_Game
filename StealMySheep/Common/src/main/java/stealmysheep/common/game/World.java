/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.game;

import stealmysheep.common.assets.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author oscar
 */
public class World {

    private final Map<String, Entity> entities = new ConcurrentHashMap<>();

    public String addEntity(Entity entity) {
        this.entities.put(entity.getId(), entity);
        return entity.getId();
    }

    public void removeEntity(String id) {
        this.entities.remove(id);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity.getId());
    }

    public <O extends Entity> List<Entity> getEntities(Class<O> entityClass) {
        List<Entity> entityList = new ArrayList<>();

        for (Entity entity : this.entities.values()) {
            if (entity.getClass().equals(entityClass)) {
                entityList.add(entity);
            }
        }
        return entityList;
    }

    public Collection<Entity> getEntities() {
        return this.entities.values();
    }

    public Entity getEntity(String ID) {
        return this.entities.get(ID);
    }
}
