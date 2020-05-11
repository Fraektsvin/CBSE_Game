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
import static stealmysheep.common.game.Input.DOWN;
import static stealmysheep.common.game.Input.LEFT;
import static stealmysheep.common.game.Input.MOUSELEFT;
import static stealmysheep.common.game.Input.RIGHT;
import static stealmysheep.common.game.Input.UP;
import static stealmysheep.common.game.Input.mouseX;
import static stealmysheep.common.game.Input.mouseY;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;
import stealmysheep.common.services.IWave;

/**
 *
 * @author nadinfariss
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})

public class PlayerControlSystem implements IUpdate, IWave {

    @Override
    public void update(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {

            Position position = player.getComponent(Position.class);
            Movement movement = player.getComponent(Movement.class);
            RangedWeapon rangedWeapon = player.getComponent(RangedWeapon.class);
            Health health = player.getComponent(Health.class);
            
            if(health.isDead()){
                world.removeEntity(player);
                return;
            }

            movement.setUp(gameData.getInput().isDown(UP));
            movement.setLeft(gameData.getInput().isDown(LEFT));
            movement.setDown(gameData.getInput().isDown(DOWN));
            movement.setRight(gameData.getInput().isDown(RIGHT));

            // added the mouse location
            float deltaX = mouseX - position.getX();
            float deltaY = mouseY - position.getY();

            position.setRadians((float) Math.atan2(deltaY, deltaX));

            if (gameData.getInput().isDown(MOUSELEFT)) {
                rangedWeapon.setIsAttacking(gameData.getInput().isDown(MOUSELEFT));
            }
            movement.update(player, gameData);
            position.update(player, gameData);

        }

    }

    @Override
    public void startWave(GameData gameData, World world, int wave) {
        for (Entity player : world.getEntities(Player.class)) {
            Health health = player.getComponent(Health.class);
            health.setHealth(health.getMaxHealth());

        }
    }

}
