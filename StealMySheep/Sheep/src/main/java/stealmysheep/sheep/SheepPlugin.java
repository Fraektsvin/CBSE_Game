/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.sheep;

import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Sheep;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;

/**
 *
 * @author NidaBasaran
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})
public class SheepPlugin implements IPlugin {

    public SheepPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        for (int i = 0; i < 4; i++) {
            Entity sheep = createSheep(gameData);
            world.addEntity(sheep);
        }

    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity sheep : world.getEntities(Sheep.class)) {
            world.removeEntity(sheep);
        }

    }

    private Entity createSheep(GameData gameData) {
        float acceleration = 60;
        float speed = 30;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float height = 50;
        float width = 70;
        float radius = 125;

        Entity sheep = new Sheep("sheep.png", radius);
        sheep.addComponent(new Movement(acceleration, speed));
        sheep.addComponent(new Position(x, y, radians));
        sheep.addComponent(new BoxCollider(height, width));

        return sheep;
    }

}
