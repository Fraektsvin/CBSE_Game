package stealmysheep.weapons;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Projectile;
import stealmysheep.common.assets.entityComponents.MeleeWeapon;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.Point;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;
import stealmysheep.common.services.IUpdate;
import stealmysheep.common.services.IWeaponsService;

@ServiceProviders({
        @ServiceProvider(service = IPlugin.class),
        @ServiceProvider(service = IUpdate.class),
        @ServiceProvider(service = IWeaponsService.class)
})
public class ProjectileManager implements IPlugin, IUpdate, IWeaponsService {
    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntities(Projectile.class);
    }

    @Override
    public void update(GameData gameData, World world) {
        world.getEntities(Projectile.class).forEach(proj -> {
            proj.decrementExpiry(gameData.getDeltaTime());
            if (proj.getExpiry() <= 0) {
                world.removeEntity(proj);
                return;
            }

            Position position = proj.getComponent(Position.class);
            Point delta = proj.getDeltaMovement().multiply(gameData.getDeltaTime());
            position.set(delta);
        });
    }

    @Override
    public void fireWeapon(World world,
                           Entity wielder,
                           Projectile.Alignment alignment,
                           Point target) {
        RangedWeapon weapon = wielder.getComponent(RangedWeapon.class);
        if (weapon == null) {
            throw new IllegalStateException(wielder + " has no RangedWeapon");
        }

        Point deltaMovement = target.unit().multiply(weapon.getVelocity());
        world.addEntity(new Projectile(weapon.getDamage(), deltaMovement, 100, alignment));
    }

    @Override
    public void swingWeapon(World world, Entity wielder, Point target) {
        MeleeWeapon weapon = wielder.getComponent(MeleeWeapon.class);
        if (weapon == null) {
            throw new IllegalStateException(wielder + " has no MeleeWeapon");
        }
    }
}
