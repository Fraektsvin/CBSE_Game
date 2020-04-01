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
import static game.Input.A;
import static game.Input.D;
import static game.Input.S;
import static game.Input.W;
import game.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IUpdate;

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

            // movement(WASD)
            movement.setUp(gameData.getKeys().isDown(W));
            movement.setLeft(gameData.getKeys().isDown(A));
            movement.setDown(gameData.getKeys().isDown(S));
            movement.setRight(gameData.getKeys().isDown(D));

            movement.update(player, gameData);

            position.update(player, gameData);

            updateShape(player);
        }
    }

    private void updateShape(Entity entity) {

        //inset images
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        Position position = entity.getComponent(Position.class);
        float x = position.getX();
        float y = position.getY();
        float radians = position.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
