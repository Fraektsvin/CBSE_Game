/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.enemy;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.Entity;
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

    private Entity sheep;

    @Override
    public void update(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Entity createThief(GameData gameData) {

        float acceleration = 60;
        float speed = 30;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float height = 50;
        float width = 70;
        boolean targetPlayer = false;
        boolean targetSheep = true;
        float targetRadius = 125;

        Entity thief = new Enemy(targetPlayer, targetSheep, targetRadius, "thief.png");
        thief.addComponent(new Movement(acceleration, speed));
        thief.addComponent(new Position(x, y, radians));
        thief.addComponent(new BoxCollider(height, width));
        return thief;
    }

    private Entity createRock(GameData gameData) {
        float acceleration = 60;
        float speed = 30;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float height = 50;
        float width = 70;
        boolean targetPlayer = true;
        boolean targetSheep = false;
        float targetRadius = 125;

        Entity rock = new Enemy(targetPlayer, targetSheep, targetRadius, "rock.png");
        rock.addComponent(new Movement(acceleration, speed));
        rock.addComponent(new Position(x, y, radians));
        rock.addComponent(new BoxCollider(height, width));
        return rock;

    }

    private Entity createArcher(GameData gameData) {
        float acceleration = 60;
        float speed = 30;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float height = 50;
        float width = 70;
        boolean targetPlayer = true;
        boolean targetSheep = false;
        float targetRadius = 125;

        Entity archer = new Enemy(targetPlayer, targetSheep, targetRadius, "archer.png");
        archer.addComponent(new Movement(acceleration, speed));
        archer.addComponent(new Position(x, y, radians));
        archer.addComponent(new BoxCollider(height, width));
        return archer;

    }

    public void moveEnemy() {

    }

}
