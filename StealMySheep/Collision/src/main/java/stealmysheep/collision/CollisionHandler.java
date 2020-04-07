package stealmysheep.collision;

import org.openide.util.lookup.ServiceProvider;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPostUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ServiceProvider(service = IPostUpdate.class)
public class CollisionHandler implements IPostUpdate {
    @Override
    public void postUpdate(GameData gameData, World world) {
        List<Collidable> list = findCollidables(world);
        list.forEach(collidable -> collidable.handle(list));
    }

    private List<Collidable> findCollidables(World world) {
        return world.getEntities().stream()
                .filter(entity -> entity.hasComponent(BoxCollider.class))
                .map(entity -> {
                    BoxCollider box = entity.getComponent(BoxCollider.class);
                    Position position = entity.getComponent(Position.class);
                    if (position == null) {
                        throw new IllegalStateException(entity + " has " + box + ", but no position.");
                    }
                    return new Collidable(entity, box, position);
                }).collect(Collectors.toList());
    }
}

class Collidable {
    private final Entity entity;
    private final BoxCollider box;
    private final Position position;

    Collidable(Entity entity, BoxCollider box, Position position) {
        this.entity = entity;
        this.box = box;
        this.position = position;
    }

    void handle(List<Collidable> list) {
        ArrayList<Entity> collidesWith = new ArrayList<>();
        boolean collTop = false;
        boolean collRight = false;
        boolean collBottom = false;
        boolean collLeft = false;

        for (Collidable other : list) {
            // Skip if same entity
            if (this == other) continue;

            // If both ranges don't overlap then skip
            if (!rangeX().overlaps(other.rangeX()) || !rangeY().overlaps(other.rangeY())) continue;

            // TODO check face

            collidesWith.add(other.entity);
        }

        box.updateCollisions(collidesWith, collTop, collRight, collBottom, collLeft);
    }

    Range rangeX() {
        return new Range(position.getX() - box.getWidth()/2, position.getX() + box.getWidth()/2);
    }

    Range rangeY() {
        return new Range(position.getY() - box.getHeight()/2, position.getY() + box.getHeight()/2);
    }
}

class Range {
    final float min;
    final float max;

    Range(float min, float max) {
        this.min = min;
        this.max = max;
    }

    boolean overlaps(Range other) {
        return max >= other.min && other.max >= min;
    }
}
