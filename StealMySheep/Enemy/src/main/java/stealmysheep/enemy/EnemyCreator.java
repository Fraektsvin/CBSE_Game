/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.enemy;

import stealmysheep.common.assets.Enemy;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Health;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;

/**
 *
 * @author Naimo
 */
public class EnemyCreator {

    public Entity createThief(GameData gameData) {

        float acceleration = 60;
        float speed = 30;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float height = 180;
        float width = 65;
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

    public Entity createRock(GameData gameData) {
        float acceleration = 60;
        float speed = 20;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float height = 180;
        float width = 215;
        boolean targetPlayer = true;
        boolean targetSheep = false;
        float targetRadius = 125;

        Entity rock = new Enemy(targetPlayer, targetSheep, targetRadius, "rock.png");
        rock.addComponent(new Movement(acceleration, speed));
        rock.addComponent(new Position(x, y, radians));
        rock.addComponent(new Health(200));
        rock.addComponent(new BoxCollider(height, width));
        return rock;

    }

    public Entity createArcher(GameData gameData) {
        float acceleration = 60;
        float speed = 35;
        float x = gameData.getSceneWidth() / 2;
        float y = gameData.getSceneHeight() / 2;
        float radians = 3.1415f / 2;
        float height = 95;
        float width = 115;
        boolean targetPlayer = true;
        boolean targetSheep = true;
        float targetRadius = 350;
        float attackRadius = 275;

        Entity archer = new Enemy(targetPlayer, targetSheep, targetRadius, attackRadius, "archer.png");
        archer.addComponent(new Movement(acceleration, speed));
        archer.addComponent(new Position(x, y, radians));
        archer.addComponent(new Health(100));
        archer.addComponent(new BoxCollider(height, width));
        archer.addComponent(new RangedWeapon(1, 1, 1, archer.getId(), 20));
        return archer;
    }

}
