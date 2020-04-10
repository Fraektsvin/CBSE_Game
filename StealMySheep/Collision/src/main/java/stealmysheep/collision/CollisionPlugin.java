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

        for (Collidable other : list) {
            // Skip if same entity
            if (this == other) continue;

            // Use Minkowski sum to check for collision and the collision side
            // We could have checked if the 4 ranges of the rectangles overlap,
            //   but this would not allow for checking which side is colliding.
            // Adapted from: https://gamedev.stackexchange.com/questions/24078/which-side-was-hit/24091#24091
            float w = (box.getWidth() + other.box.getWidth()) / 2;
            float h = (box.getHeight() + other.box.getHeight()) / 2;
            float dx = position.getX() - other.position.getX();
            float dy = position.getY() - other.position.getY();

            // Check for collision
            if (!(Math.abs(dx) <= w && Math.abs(dy) <= h)) continue;

            collidesWith.add(other.entity);

            // Check which sides collides
            float wy = w * dy;
            float hx = h * dx;

            if (wy > -hx) {
                if (wy < -hx) collTop = true;
                else collLeft = true;
            } else {
                if (wy < -hx) collRight = true;
                else collBottom = true;
            }
        }

        box.updateCollisions(collidesWith, collTop, collRight, collBottom, collLeft);
    }
}
