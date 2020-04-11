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

/**
 *
 * @author nadinfariss
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})

public class PlayerControlSystem implements IUpdate {

    @Override
    public void update(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            Position position = player.getComponent(Position.class);
            Movement movement = player.getComponent(Movement.class);

            movement.setUp(gameData.getInput().isDown(UP));
            movement.setLeft(gameData.getInput().isDown(LEFT));
            movement.setDown(gameData.getInput().isDown(DOWN));
            movement.setRight(gameData.getInput().isDown(RIGHT));

            // added the mouse location
            float deltaX = mouseX - position.getX();
            float deltaY = mouseY - position.getY();

            RangedWeapon rangedWeapon = player.getComponent(RangedWeapon.class);
            rangedWeapon.setIsAttacking(gameData.getInput().isDown(MOUSELEFT));

            position.setRadians((float) Math.atan2(deltaX, deltaY));

            movement.update(player, gameData);

            position.update(player, gameData);

        }

    }

}
