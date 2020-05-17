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
import stealmysheep.common.assets.entityComponents.Health;
import stealmysheep.common.assets.entityComponents.MeleeWeapon;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
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

    public EnemyControlSystem() {
        this.random = new Random();
    }

    @Override
    public void update(GameData gameData, World world) {

        this.ai = lookup.lookup(IAI.class);

        for (Entity enemy : world.getEntities(Enemy.class)) {
            Enemy currentEnemy = (Enemy) enemy;
            handleHealth(world, currentEnemy);
            if (checkTargetExistence(world, currentEnemy)) {
                Position targetPosition = currentEnemy.getTarget().getComponent(Position.class);
                Node goal = new Node(targetPosition.getX(), targetPosition.getY());

                if (ai != null) {
                    this.ai.moveEntity(currentEnemy, goal, world, gameData);
                }

            } else {
                setTarget(world, currentEnemy);
            }
            handleTarget(world, currentEnemy);
            // hvis et enemy går efter får og willtarget også er true så skal der ske at hvis spilleren er en indenfor targetradius så skal dens mål ikke længere være sheep men player 
        }
    }

    private void handleTarget(World world, Enemy enemy) {
        if (world.getEntities(Player.class).isEmpty()) {
            return;
        }
        Entity player = world.getEntities(Player.class).get(0);
        if (player == null) {
            return;
        }
        Position playerPosition = player.getComponent(Position.class);
        Position enemyPosition = enemy.getComponent(Position.class);
        if (enemy.getTarget() != null) {
            if (enemy.getTarget().getClass().equals(Sheep.class)) {
                if (enemy.willTargetPlayer()) {
                    if ((Math.pow(playerPosition.getX() - enemyPosition.getX(), 2) + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2)) < Math.pow(enemy.getTargetRadius(), 2)) {
                        setTargetPlayer(world, enemy);
                    }
                }
                steal(enemy, world);

            } else if (enemy.getTarget().getClass().equals(Player.class)) {
                if (enemy.willTargetSheep() && !((Math.pow(playerPosition.getX() - enemyPosition.getX(), 2) + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2)) < Math.pow(enemy.getTargetRadius(), 2))) {
                    setTargetSheep(world, enemy);
                } else if ((Math.pow(playerPosition.getX() - enemyPosition.getX(), 2) + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2)) < Math.pow(enemy.getAttackRadius(), 2)) {
                    attack(enemy, enemy.getTarget());
                }

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
        if (world.getEntities(Player.class).isEmpty()) {
            return;
        }
        Entity player = world.getEntities(Player.class).get(0);
        if (player == null) {
            return;
        }
        Position playerPosition = player.getComponent(Position.class);
        Position enemyPosition = enemy.getComponent(Position.class);

        if ((Math.pow(playerPosition.getX() - enemyPosition.getX(), 2) + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2)) < Math.pow(enemy.getTargetRadius(), 2)) {
            enemy.setTarget(player);
        }
    }

    private void setTargetSheep(World world, Enemy enemy) {
        if (world.getEntities(Sheep.class).size() <= 0) {
            return;
        }
        int randomSheep = random.nextInt(world.getEntities(Sheep.class).size());

        enemy.setTarget(world.getEntities(Sheep.class).get(randomSheep));
    }

    private void handleHealth(World world, Enemy enemy) {
        Health health = enemy.getComponent(Health.class);
        if (health == null) {
            return;
        }

        if (health.isDead()) {
            world.removeEntity(enemy);
        }

    }

    private void attack(Enemy enemy, Entity target) {
        Position position = enemy.getComponent(Position.class);
        Position targetPosition = target.getComponent(Position.class);

        RangedWeapon rangedWeapon = enemy.getComponent(RangedWeapon.class);
        MeleeWeapon meleeWeapon = enemy.getComponent(MeleeWeapon.class);

        float deltaX = targetPosition.getX() - position.getX();
        float deltaY = targetPosition.getY() - position.getY();

        if (rangedWeapon != null) {
            position.setRadians((float) Math.atan2(deltaY, deltaX));
            rangedWeapon.setIsAttacking(true);
        }

        if (meleeWeapon != null) {
            position.setRadians((float) Math.atan2(deltaY, deltaX));
            meleeWeapon.setIsAttacking(true);
        }
    }

    private void steal(Enemy enemy, World world) {
        Entity target = enemy.getTarget();
        Position position = enemy.getComponent(Position.class);
        Position position2 = target.getComponent(Position.class);

        BoxCollider collider = enemy.getComponent(BoxCollider.class);
        BoxCollider collider2 = target.getComponent(BoxCollider.class);

        float x1 = position.getX() - collider.getWidth() / 2;
        float x2 = position.getX() + collider.getWidth() / 2;
        float x3 = position2.getX() - collider2.getWidth() / 2;
        float x4 = position2.getX() + collider2.getWidth() / 2;

        float y1 = position.getY() - collider.getHeight() / 2;
        float y2 = position.getY() + collider.getHeight() / 2;
        float y3 = position2.getY() - collider2.getHeight() / 2;
        float y4 = position2.getY() + collider2.getHeight() / 2;

        if ((x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)) {
            world.removeEntity(target);
        }
    }

}
