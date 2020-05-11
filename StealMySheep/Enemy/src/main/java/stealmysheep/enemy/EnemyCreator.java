/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.enemy;

import java.util.List;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.SpawnPoint;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Health;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IWave;

/**
 *
 * @author Naimo
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IWave.class),})
public class EnemyCreator implements IWave {

    private final int thiefSpawn = 0;
    private final int archerSpawn = 3;
    private final int rockSpawn = 5;
    private final Random random = new Random();

    @Override
    public void startWave(GameData gameData, World world, int wave) {
        float multiplier = wave / 2;
        if (multiplier < 1) {
            multiplier = 1;
        }

        List<Entity> spawns = world.getEntities(SpawnPoint.class);

        if (wave >= thiefSpawn) {
            int x = 0;
            while (x <= 3 * multiplier) {
                Entity spawn = spawns.get(this.random.nextInt(spawns.size()));
                Position position = spawn.getComponent(Position.class);
                float spawnX = position.getX();
                float spawnY = position.getY();

                world.addEntity(createThief(spawnX, spawnY));

                x++;
            }
        }

        if (wave >= archerSpawn) {
            int x = 0;
            while (x <= 1.7 * multiplier) {
                Entity spawn = spawns.get(this.random.nextInt(spawns.size()));
                Position position = spawn.getComponent(Position.class);
                float spawnX = position.getX();
                float spawnY = position.getY();

                world.addEntity(createArcher(spawnX, spawnY));

                x++;
            }
        }

//        if (wave >= rockSpawn) {
//            int x = 0;
//            while (x <= 1.2 * multiplier) {
//                Entity spawn = spawns.get(this.random.nextInt(spawns.size()));
//                Position position = spawn.getComponent(Position.class);
//                float spawnX = position.getX();
//                float spawnY = position.getY();
//
//                world.addEntity(createRock(spawnX, spawnY));
//
//                x++;
//            }
//        }
    }

    private void spawnArcher(int wave, float multiplier, List<Entity> spawns, World world) {
        if (wave >= archerSpawn) {
            int x = 0;
            while (x <= 2 * multiplier) {
                Entity spawn = spawns.get(this.random.nextInt(spawns.size()));
                Position position = spawn.getComponent(Position.class);
                float spawnX = position.getX();
                float spawnY = position.getY();

                world.addEntity(createThief(spawnX, spawnY));

                x++;
            }
        }
    }

    private Entity createThief(float x, float y) {
        float acceleration = 60;
        float speed = 30;
        float radians = 3.1415f / 2;
        float height = 70;
        float width = 30;
        boolean targetPlayer = false;
        boolean targetSheep = true;
        float targetRadius = 125;

        Entity thief = new Enemy(targetPlayer, targetSheep, targetRadius, "thief.png");
        thief.addComponent(new Movement(acceleration, speed));
        thief.addComponent(new Position(x, y, radians));
        thief.addComponent(new Health(100));
        thief.addComponent(new BoxCollider(height, width));
        return thief;
    }

    private Entity createRock(float x, float y) {
        float acceleration = 60;
        float speed = 15;
        float radians = 3.1415f / 2;
        float height = 85;
        float width = 98;
        boolean targetPlayer = true;
        boolean targetSheep = false;
        float targetRadius = 125;

        Entity rock = new Enemy(targetPlayer, targetSheep, targetRadius, "rock.png");
        rock.addComponent(new Movement(acceleration, speed));
        rock.addComponent(new Position(x, y, radians));
        rock.addComponent(new Health(300));
        rock.addComponent(new BoxCollider(height, width));
        return rock;

    }

    private Entity createArcher(float x, float y) {
        float acceleration = 60;
        float speed = 30;
        float radians = 3.1415f / 2;
        float height = 52;
        float width = 45;
        boolean targetPlayer = true;
        boolean targetSheep = true;
        float targetRadius = 350;
        float attackRadius = 275;
        float shotCooldown = 2;

        Entity archer = new Enemy(targetPlayer, targetSheep, targetRadius, attackRadius, "archer.png");
        archer.addComponent(new Movement(acceleration, speed));
        archer.addComponent(new Position(x, y, radians));
        archer.addComponent(new Health(100));
        archer.addComponent(new BoxCollider(height, width));
        archer.addComponent(new RangedWeapon(1, 1, shotCooldown, archer.getId(), 20));
        return archer;
    }

}
