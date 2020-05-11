/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.thief;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.ai.IAI;
import stealmysheep.common.ai.Node;
import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;

/**
 *
 * @author nadinfariss
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})
public class TheifUpdate implements IUpdate {

    private IAI ai;
    private final Lookup lookup = Lookup.getDefault();

    @Override
    public void update(GameData gameData, World world) {
        this.ai = lookup.lookup(IAI.class);
        if (ai == null) {
            return;
        }
        for (Entity thief : world.getEntities(Enemy.class)) {
            Enemy currentThief = (Enemy) thief;
            Position position = thief.getComponent(Position.class);
            Movement movement = thief.getComponent(Movement.class);
            BoxCollider collider = thief.getComponent(BoxCollider.class);

            if (currentThief.isMoving() == true) {
                Node goal = new Node(currentThief.getX(), currentThief.getY());
                ArrayList<Node> path = ai.greedyBestFirstSearch(thief, goal, world);

                if (!path.isEmpty()) {
                    float x = path.get(path.size() - 1).getX() - position.getX();
                    float y = path.get(path.size() - 1).getY() - position.getY();
                    position.setRadians((float) Math.atan2(y, x));

                    position.setX(player.getY + float) cos(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime()
                    );
                    position.setY(position.getY() + (float) sin(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime());
                    // player.getY and x

                    if (ai.checkPointCollider(goal.getX(), goal.getY(), position.getX(), position.getY(), collider)) {
                        currentThief.setMoving(false);
                    }

                } else {
                    currentThief.setMoving(false);
                }
                //ai

            }
        }
    }
}
