/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.enemy;

import java.util.Random;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.ai.IAI;
import stealmysheep.common.ai.Node;
import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Player;
import stealmysheep.common.assets.Sheep;
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
public class EnemyControlSystem implements IUpdate {

    private Entity sheep;
    private Entity player;
    private EnemyCreator enemy = new EnemyCreator();
    private Random random;
    private IAI ai;
    private final Lookup lookup = Lookup.getDefault();

    @Override
    public void update(GameData gameData, World world) {
        this.ai = lookup.lookup(IAI.class);
        if (ai == null) {
            return;
        }
        for (Entity enemy : world.getEntities(Enemy.class)) {
            Enemy thief = (Enemy) enemy;
            Enemy rock = (Enemy) enemy;
            Enemy archer = (Enemy) enemy;
            //Sheep currentSheep = (Sheep) sheep;
            Player currentPlayer = (Player) player;
            BoxCollider collider = enemy.getComponent(BoxCollider.class);
            Position position = enemy.getComponent(Position.class);
            Movement movement = enemy.getComponent(Movement.class);

            if (thief.isMoving() == true) {
                Node goal = new Node(Sheep currentSheep = (Sheep) sheep.ge.getX(), currentSheep.getY());
            }

            for (Entity entity : world.getEntities()) {
                if (entity.hasComponent(BoxCollider.class) && entity.hasComponent(Position.class) && !entity.equals(currentSheep)) {
                    BoxCollider entityCollider = entity.getComponent(BoxCollider.class);
                    Position entityPosition = entity.getComponent(Position.class);
                    if (entityCollider.checkPointCollider(x, y, entityPosition.getX(), entityPosition.getY())) {
                        currentSheep.setMoving(false);
                    }
                }
            }
            //System.out.println("Position: x=" + position.getX() + "  y=" + position.getY() + "       Goal: x=" + goal.getX() + "  y=" + goal.getY());
            this.ai.moveEntity(thief, rock, archer, goal, world, gameData);
            if (collider.checkPointCollider(goal.getX(), goal.getY(), position.getX(), position.getY())) {
                System.out.println("Goal reached");
                currentSheep.setMoving(false);

            }

        }
    }

}

private boolean checkTargetExistence(World world, Enemy enemy) {
        // tjekker om target findes og return true når den findes og false når den ikke eksisterer.
        Entity target = enemy.getTarget();
        if(target == null){
            
            return false;
        }
        Entity entity = world.getEntity(target.getId());
        if(entity == null){
            return false;
        }else{
            return true;

}
    }
    private void setTargetPlayer(World world, Enemy enemy){
        if (enemy.willTargetPlayer()) {
            Entity player = world.getEntities(Player.class  

    ).get(0);
    if (player

    
        == null) {
                return;
    }

    Position playerPosition = player.getComponent(Position.class);
    Position enemyPosition = enemy.getComponent(Position.class);

    if ((Math.pow (playerPosition.getX

    () - enemyPosition.getX(), 2) + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2)) < Math.pow(enemy.getTargetRadius 
        (), 2)) {
                enemy.setTarget(player);
    }
}


}

    private void setTargetSheep(World world, Enemy enemy) {
// hvis begge (tjek modul beskrivelser) true første prio lig sheep. 
// hvis den første er true og den anden er false er første prio player. 
// første metode der gøres brug af ligeså snart der spawnes.
        if (enemy.willTargetSheep()) {
            int randomSheep = random.nextInt(world.getEntities(Sheep.class  

    ).size());
    if (randomSheep

    
        == 0) {
                return;
    }

    enemy.setTarget (world.getEntities

    (Sheep.class  
    

).get(randomSheep));
        }
    }

    private void attack() {
        

    }
}
