package stealmysheep.collision;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;
import stealmysheep.common.services.IPostUpdate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This service is responsible for checking for collisions.
 * It does not affect the game directly. Collisions are communicated via the BoxCollider class.
 *
 * @author frmik18
 */
@ServiceProviders({
        @ServiceProvider(service = IPostUpdate.class),
        @ServiceProvider(service = IPlugin.class),
})
public class CollisionPlugin implements IPostUpdate, IPlugin {

    @Override
    public void start(GameData gameData, World world) {
        // Ignore
    }

    /**
     * Reset collisions
     */
    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities().forEach(entity -> {
            BoxCollider box = entity.getComponent(BoxCollider.class);
            if (box != null) box.updateCollisions(Collections.emptyList(), false, false, false, false);
        });
    }

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

/**
 * Helper class representing an Entity with components required for collision detection.
 *
 * @author frmik18
 */
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

        Range rx1 = rangeX();
        Range ry1 = rangeY();
        for (Collidable other : list) {
            // Skip if same entity
            if (this == other) continue;
            Range rx2 = other.rangeX();
            Range ry2 = other.rangeY();

            // If both ranges don't overlap then skip
            if (!(rx1.overlaps(rx2) && ry1.overlaps(ry2))) continue;

            // Check which faces are colliding
            if (ry1.min <= ry2.min) collTop = true;
            if (rx1.min <= rx2.min) collRight = true;
            if (ry1.max >= ry2.max) collBottom = true;
            if (rx1.max >= rx2.max) collLeft = true;

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

/**
 * Helper class to deal with range overlap
 *
 * @author frmik18
 */
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
