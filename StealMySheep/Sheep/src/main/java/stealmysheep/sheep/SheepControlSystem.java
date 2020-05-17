/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.sheep;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.Random;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.ai.IAI;
import stealmysheep.common.ai.Node;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Sheep;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;

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
        
        if (world.getEntities(Sheep.class).isEmpty()) {
            gameData.setEndGame(true);
        }
        
        for (Entity sheep : world.getEntities(Sheep.class)) {
            Sheep currentSheep = (Sheep) sheep;
            Position position = sheep.getComponent(Position.class);
            BoxCollider collider = sheep.getComponent(BoxCollider.class);
            
            if (currentSheep.isMoving() == false) {
                
                if (random.nextInt(600) + 1 <= 2) {
                    currentSheep.setMoving(true);
                    Random random = new Random();
                    float a = (float) (random.nextFloat() * 2 * Math.PI);
                    float r = (float) (currentSheep.getRadius() * sqrt(random.nextFloat()));
                    float x = (float) (r * cos(a));
                    float y = (float) (r * sin(a));
                    currentSheep.setX(position.getX() + x);
                    currentSheep.setY(position.getY() + y);
                    
                    for (Entity entity : world.getEntities()) {
                        if (entity.hasComponent(BoxCollider.class) && entity.hasComponent(Position.class) && !entity.equals(currentSheep)) {
                            BoxCollider entityCollider = entity.getComponent(BoxCollider.class);
                            Position entityPosition = entity.getComponent(Position.class);
                            if (entityCollider.checkPointCollider(x, y, entityPosition.getX(), entityPosition.getY())) {
                                currentSheep.setMoving(false);
                            }
                        }
                    }
                }
                
            } else if (currentSheep.isMoving() == true) {
                Node goal = new Node(currentSheep.getX(), currentSheep.getY());

                //System.out.println("Position: x=" + position.getX() + "  y=" + position.getY() + "       Goal: x=" + goal.getX() + "  y=" + goal.getY());
                this.ai.moveEntity(sheep, goal, world, gameData);
                
                if (collider.checkPointCollider(goal.getX(), goal.getY(), position.getX(), position.getY())) {
                    System.out.println("Goal reached");
                    currentSheep.setMoving(false);
                    
                }
            }
        }
    }
}
