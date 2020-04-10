package stealmysheep.collision;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Position;

class MockEntity extends Entity {
    final BoxCollider box = new BoxCollider(1, 1);
    final Position pos;

    MockEntity(float x, float y) {
        pos = new Position(x, y, 0);
        addComponent(box);
        addComponent(pos);
    }

    @Override
    public String toString() {
        return "(" + pos.getX() + ", " + pos.getY() + ")";
    }
}
