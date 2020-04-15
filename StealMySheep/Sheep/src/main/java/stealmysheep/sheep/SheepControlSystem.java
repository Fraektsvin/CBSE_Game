/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.sheep;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Sheep;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Movement;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import static stealmysheep.common.game.Input.UP;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;

/**
 *
 * @author NidaBasaran
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})
public class SheepControlSystem implements IUpdate {

    private Random random = new Random();
    //private int processCounter = 0;
    private int processCounter;

    @Override
    public void update(GameData gameData, World world) {
        for (Entity sheep : world.getEntities(Sheep.class)) {
            Position position = sheep.getComponent(Position.class);
            Movement movement = sheep.getComponent(Movement.class);
            Sheep currentSheep = (Sheep) sheep;
            if (currentSheep.isMoving() == false) {
                if (random.nextInt(100) + 1 <= 5) {
                    currentSheep.setMoving(true);
                    float a = (float) (Math.random() * 2 * Math.PI);
                    float r = (float) (currentSheep.getRadius() * sqrt(Math.random()));
                    float x = (float) (r * cos(a));
                    float y = (float) (r * sin(a));
                    currentSheep.setX(x);
                    currentSheep.setY(y);

                } else if (currentSheep.isMoving() == true) {

                }

            }
        }
    }

    private void moveSheep(Sheep sheep) {

    }

    private void insert() {

    }

    private void insertAll() {
    }

    private void remove() {

    }

    private void expand() {

    }

    private float heuristic(float x1, float x2, float y1, float y2) {
        float vec = (float) Math.sqrt(Math.pow((double) (x2 - x1), 2) + Math.pow((double) (y2 - y1), 2));
        return vec;
    }

    //BoxCollider box = sheeps.getComponent(SheepPlugin.class);
    //if(sheeps.isMoving() == false){
    //}else if(sheeps.isMoving() == true){
    /**
     * if (processCounter % 5 == 0) { movement.setLeft(random.nextBoolean());
     * }else if(processCounter % 5 == 1){
     * movement.setRight(random.nextBoolean()); }else if(processCounter % 5 ==
     * 2){ movement.setDown(random.nextBoolean()); }else if(processCounter % 5
     * == 3){ movement.setUp(random.nextBoolean()); }
     *
     * movement.update(sheep, gameData); position.update(sheep, gameData);
     *
     * processCounter++;
             *
     */
//            Movement movement = sheep.getComponent(Movement.class);
//
//              if (processCounter % 10 == 0) {
//                movement.setLeft(random.nextBoolean());
//                movement.setRight(random.nextBoolean());
//            }
//            movement.setUp(random.nextBoolean());
//
//            movement.update(sheep, gameData);
//            position.update(sheep, gameData);
//
//            processCounter++;
}
