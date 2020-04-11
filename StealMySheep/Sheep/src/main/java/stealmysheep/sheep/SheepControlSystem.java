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
//
      private Random random = new Random();
      //private int processCounter = 0;
      private int processCounter;
      private Sheep sheeps = new Sheep();
         

    @Override
    public void update(GameData gameData, World world) {
        for (Entity sheep : world.getEntities(Sheep.class)) {
            Position position = sheep.getComponent(Position.class);
            Movement movement = sheep.getComponent(Movement.class);
            //BoxCollider box = sheeps.getComponent(SheepPlugin.class);

            //if(sheeps.isMoving() == false){
                            //}else if(sheeps.isMoving() == true){
            if (processCounter % 5 == 0 && sheeps.getRadius() == 6) {
                movement.setLeft(random.nextBoolean());
            }else if(processCounter % 5 == 1 && sheeps.getRadius() == 6){
                movement.setRight(random.nextBoolean());
            }else if(processCounter % 5 == 2 && sheeps.getRadius() == 6){
                movement.setDown(random.nextBoolean());
            }else if(processCounter % 5 == 3 && sheeps.getRadius() == 6){
                movement.setUp(random.nextBoolean());
            }
            
            movement.update(sheep, gameData);
            position.update(sheep, gameData);

            processCounter++;
                
            }
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
 }
    
