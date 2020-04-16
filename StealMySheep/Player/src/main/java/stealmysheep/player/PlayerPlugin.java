/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.player;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Player;
import stealmysheep.common.assets.entityComponents.Health;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;

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

        float acceleration = 600;
        float speed = 300;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float radius = 8;

        Entity player = new Player("player.png");
        player.addComponent(new Movement(acceleration, speed));
        player.addComponent(new Position(x, y, radians));
        player.addComponent(new Health(100));
        //add Range wepon
        int magazineSize = 5;
        float reloadTime = 3;
        float shotCooldown = 0.5f;
        String entityId = player.getId();
        int damage = 50;
        player.addComponent(new RangedWeapon(magazineSize, reloadTime, shotCooldown, entityId, damage));

        //add melee weapon
        return player;
    }

}
