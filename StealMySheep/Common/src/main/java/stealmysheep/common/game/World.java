/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.common.game;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    public <T extends Entity> void removeEntities(Class<T> type) {
        this.entities.values().forEach(entity -> {
            if (type.isInstance(entity)) removeEntity(entity);
        });
    }

    public <T extends Entity> List<T> getEntities(Class<T> entityClass) {
        List<T> entityList = new ArrayList<>();

        for (Entity entity : this.entities.values()) {
            if (entityClass.isInstance(entity)) {
                //noinspection unchecked
                entityList.add((T) entity);
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

    public <C extends Component> Collection<Entity> getEntitiesWithComponent(Class<C> type) {
        return this.entities.values().stream()
                .filter(e -> e.hasComponent(type))
                .collect(Collectors.toList());
    }
}
