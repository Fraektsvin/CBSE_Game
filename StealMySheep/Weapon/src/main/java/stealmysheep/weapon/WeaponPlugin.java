/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.weapon;

import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Health;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;

/**
 *
 * @author oscar
 */
@ServiceProvider(service = IPlugin.class)
public class WeaponPlugin implements IPlugin {
    
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 5; i++) {
            Entity entity = new Entity("thief.png");
            
            Random random = new Random();
            float radians = 2 * 3.14f * random.nextFloat();
            float x = random.nextInt(gameData.getSceneWidth());
            float y = random.nextInt(gameData.getSceneHeight());
            
            entity.addComponent(new Position(x, y, radians));
            entity.addComponent(new BoxCollider(92, 34));
            entity.addComponent(new Health(100));
            
            world.addEntity(entity);
            
        }
    }
    
    @Override
    public void stop(GameData gameData, World world) {
    }
}
