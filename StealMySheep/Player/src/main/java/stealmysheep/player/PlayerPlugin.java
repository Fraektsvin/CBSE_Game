/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.player;

import assets.Entity;
import assets.Player;
import assets.entityComponents.Movement;
import assets.entityComponents.Position;
import game.GameData;
import game.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IPlugin;

/**
 *
 * @author nadinfariss
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPlugin.class),})

public class PlayerPlugin implements IPlugin {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

    private Entity createPlayer(GameData gameData) {

        float acceleration = 200;
        float maxSpeed = 300;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float radius = 8;

        Entity player = new Player();
        player.addComponent(new Movement(acceleration, maxSpeed));
        player.addComponent(new Position(x, y, radians));
        player.setRadius(radius);

        return player;
    }

}
