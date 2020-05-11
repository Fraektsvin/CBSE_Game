/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.ai;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.ai.IAI;
import stealmysheep.common.ai.Node;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;

/**
 *
 * @author NidaBasaran
 *
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IAI.class),})

public class AIController implements IAI {

    AstarAI ai;

    public AIController() {
        this.ai = new AstarAI();
    }

    @Override
    public void moveEntity(Entity entity, Node goal, World world, GameData gameData) {
        Position position = entity.getComponent(Position.class);
        Movement movement = entity.getComponent(Movement.class);

        if (position == null || movement == null) {
            return;
        }

        ArrayList<Node> path = this.ai.findAstarPath(entity, goal, world);

        if (!path.isEmpty()) {

            float x = path.get(path.size() - 1).getX() - position.getX();
            float y = path.get(path.size() - 1).getY() - position.getY();
            position.setRadians((float) Math.atan2(y, x));

            position.setX(position.getX() + (float) cos(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime());
            position.setY(position.getY() + (float) sin(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime());
        }
    }

}
