package stealmysheep.enemy;

import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Player;
import stealmysheep.common.assets.SpawnPoint;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author nadinfariss
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class EnemyPlugin implements IPlugin {

    private final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 4; i++) {
            world.addEntity(createSpawnLeft());
            world.addEntity(createSpawnRight());
            world.addEntity(createSpawnTop());
            world.addEntity(createSpawnBottom());
        }

    }

    private Entity createSpawnLeft() {
        float x = 0;
        float y = random.nextInt(840);

        Entity spawn = new SpawnPoint();
        spawn.addComponent(new Position(x, y, 3.1415f / 2));
        return spawn;
    }

    private Entity createSpawnRight() {
        float x = 840;
        float y = random.nextInt(840);

        Entity spawn = new SpawnPoint();
        spawn.addComponent(new Position(x, y, 3.1415f / 2));
        return spawn;
    }

    private Entity createSpawnTop() {
        float x = random.nextInt(840);
        float y = 840;

        Entity spawn = new SpawnPoint();
        spawn.addComponent(new Position(x, y, 3.1415f / 2));
        return spawn;
    }

    private Entity createSpawnBottom() {
        float x = random.nextInt(840);
        float y = 0;

        Entity spawn = new SpawnPoint();
        spawn.addComponent(new Position(x, y, 3.1415f / 2));
        return spawn;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }

    }
}
