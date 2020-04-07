package stealmysheep.collision;

import org.openide.util.lookup.ServiceProvider;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPostUpdate;

@ServiceProvider(service = IPostUpdate.class)
public class CollisionHandler implements IPostUpdate {
    @Override
    public void postUpdate(GameData gameData, World world) {
        
    }
}
