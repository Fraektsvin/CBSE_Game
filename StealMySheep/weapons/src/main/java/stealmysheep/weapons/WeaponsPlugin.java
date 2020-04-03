package stealmysheep.weapons;

import stealmysheep.common.assets.entityComponents.MeleeWeapon;
import stealmysheep.common.assets.Projectile;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.Point;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;
import stealmysheep.common.services.IUpdate;

public class WeaponsPlugin implements IPlugin, IUpdate {
    @Override
    public void start(GameData gameData, World world) {}

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntities(Projectile.class);
    }

    @Override
    public void update(GameData gameData, World world) {
        world.getEntitiesWithComponent(RangedWeapon.class).forEach(entity -> {

        });
        world.getEntitiesWithComponent(MeleeWeapon.class).forEach(entity -> {

        });
        world.getEntities(Projectile.class).forEach(proj -> {
            Position position = proj.getComponent(Position.class);
            Point delta = proj.getDeltaMovement().times(gameData.getDeltaTime());
            position.set(delta);
        });
    }
}
