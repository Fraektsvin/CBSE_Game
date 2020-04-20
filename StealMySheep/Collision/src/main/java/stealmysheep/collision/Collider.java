package stealmysheep.collision;

import org.openide.util.lookup.ServiceProvider;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPostUpdate;

@ServiceProvider(service = IPostUpdate.class)
public class Collider implements IPostUpdate {

    @Override
    public void postUpdate(GameData gameData, World world) {
        for (Entity a : world.getEntities()) {
            for (Entity b : world.getEntities()) {
                if (collides(a, b)) {
                    collide(a, b);
                }
            }
        }
    }

    /**
     * Use Minkowski sum to check for collision between rectangles. Adapted
     * from:
     * https://gamedev.stackexchange.com/questions/24078/which-side-was-hit/24091#24091
     */
    private boolean collides(Entity a, Entity b) {
        if (a.equals(b)) {
            return false;
        }
        Position posA = a.getComponent(Position.class);
        Position posB = b.getComponent(Position.class);
        BoxCollider boxA = a.getComponent(BoxCollider.class);
        BoxCollider boxB = b.getComponent(BoxCollider.class);

        if (posA == null || posB == null || boxA == null || boxB == null) {
            return false;
        }

        float w = (boxA.getWidth() + boxB.getWidth()) / 2;
        float h = (boxA.getHeight() + boxB.getHeight()) / 2;
        float dx = posA.getX() - posB.getX();
        float dy = posA.getY() - posB.getY();

        return Math.abs(dx) <= w && Math.abs(dy) <= h;
    }

    private void collide(Entity entityA, Entity entityB) {
        Position positionA = entityA.getComponent(Position.class);
        Position positionB = entityB.getComponent(Position.class);
        BoxCollider colliderA = entityA.getComponent(BoxCollider.class);
        BoxCollider colliderB = entityB.getComponent(BoxCollider.class);

        float xDifference = positionA.getX() - positionB.getX();
        float yDifference = positionA.getY() - positionB.getY();

        float overlapX = colliderA.getWidth() / 2 + colliderB.getWidth() / 2 - Math.abs(xDifference);
        float overlapY = colliderA.getHeight() / 2 + colliderB.getHeight() / 2 - Math.abs(yDifference);

        if (entityA.hasComponent(Movement.class)) {
            if (overlapX >= overlapY) {
                if (yDifference > 0) {
                    positionA.setY(positionA.getY() + overlapY);
                } else {
                    positionA.setY(positionA.getY() - overlapY);

                }
            } else {
                if (xDifference > 0) {
                    positionA.setX(positionA.getX() + overlapX);
                } else {
                    positionA.setX(positionA.getX() - overlapX);
                }
            }

        }

    }

}
