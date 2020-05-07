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
            Enemy currentEnemy = (Enemy) enemy;
            BoxCollider collider = enemy.getComponent(BoxCollider.class);
            Position position = enemy.getComponent(Position.class);
            Movement movement = enemy.getComponent(Movement.class);

            if (checkTargetExistence(world, currentEnemy)) {
                Position targetPosition = currentEnemy.getTarget().getComponent(Position.class);
                Node goal = new Node(targetPosition.getX(), targetPosition.getY());
                this.ai.moveEntity(currentEnemy, goal, world, gameData);
            }else{
                setTarget(world, currentEnemy);
            }
        }
    }

    private boolean checkTargetExistence(World world, Enemy enemy) {
        // tjekker om target findes og return true når den findes og false når den ikke eksisterer.
        Entity target = enemy.getTarget();
        if (target == null) {

            return false;
        }
        Entity entity = world.getEntity(target.getId());
        if (entity == null) {
            return false;
        } else {
            return true;

        }
    }

    private void setTarget(World world, Enemy enemy) {
        if (enemy.willTargetPlayer()) {
            setTargetPlayer(world, enemy);
        }
        if (enemy.willTargetSheep()) {
            setTargetSheep(world, enemy);
        }
    }

    private void setTargetPlayer(World world, Enemy enemy) {

        Entity player = world.getEntities(Player.class
        ).get(0);
        if (player
                == null) {
            return;
        }

        Position playerPosition = player.getComponent(Position.class);
        Position enemyPosition = enemy.getComponent(Position.class);

        if ((Math.pow(playerPosition.getX() - enemyPosition.getX(), 2) + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2)) < Math.pow(enemy.getTargetRadius(), 2)) {
            enemy.setTarget(player);
        }
    }

    private void setTargetSheep(World world, Enemy enemy) {
// hvis begge (tjek modul beskrivelser) true første prio lig sheep. 
// hvis den første er true og den anden er false er første prio player. 
// første metode der gøres brug af ligeså snart der spawnes.
        int randomSheep = random.nextInt(world.getEntities(Sheep.class
        ).size());
        if (randomSheep
                == 0) {
            return;
        }

        enemy.setTarget(world.getEntities(Sheep.class
        ).get(randomSheep));

    }

    private void attack() {

    }
}
