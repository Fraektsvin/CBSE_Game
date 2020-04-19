/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.sheep;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.AI.IAI;
import stealmysheep.common.AI.Node;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Sheep;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
/**
 *
 * @author NidaBasaran
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})
public class SheepControlSystem implements IUpdate {
    
    private IAI ai;

    private Random random = new Random();
    private final Lookup lookup = Lookup.getDefault();

    @Override
    public void update(GameData gameData, World world) {
        this.ai = lookup.lookup(IAI.class);
        if (ai == null) {
            return;
        }
        for (Entity sheep : world.getEntities(Sheep.class)) {
            Sheep currentSheep = (Sheep) sheep;
            Position position = sheep.getComponent(Position.class);
            Movement movement = sheep.getComponent(Movement.class);
            BoxCollider collider = sheep.getComponent(BoxCollider.class);

            if (currentSheep.isMoving() == false) {
                if (random.nextInt(100) + 1 <= 2) {
                    currentSheep.setMoving(true);
                    Random random = new Random();
                    float a = (float) (random.nextFloat() * 2 * Math.PI);
                    float r = (float) (currentSheep.getRadius() * sqrt(random.nextFloat()));
                    float x = (float) (r * cos(a));
                    float y = (float) (r * sin(a));
                    currentSheep.setX(position.getX() + x);
                    currentSheep.setY(position.getY() + y);
                }
            } else if (currentSheep.isMoving() == true) {
                Node goal = new Node(currentSheep.getX(), currentSheep.getY());
                ArrayList<Node> path = ai.greedyBestFirstSearch(sheep, goal, world);

                if (!path.isEmpty()) {
                    float x = path.get(path.size() - 1).getX() - position.getX();
                    float y = path.get(path.size() - 1).getY() - position.getY();
                    position.setRadians((float) Math.atan2(y, x));

                    position.setX(position.getX() + (float) cos(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime());
                    position.setY(position.getY() + (float) sin(position.getRadians()) * movement.getSpeed() * gameData.getDeltaTime());

                    if (ai.checkPointCollider(goal.getX(), goal.getY(), position.getX(), position.getY(), collider)) {
                        currentSheep.setMoving(false);
                    }
                }

            }
        }
    }
}


    