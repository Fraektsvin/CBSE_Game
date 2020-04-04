package stealmysheep.weapons;

import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Projectile;
import stealmysheep.common.assets.Swing;
import stealmysheep.common.assets.entityComponents.MeleeWeapon;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.Point;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IPlugin;
import stealmysheep.common.services.IPostUpdate;
import stealmysheep.common.services.IUpdate;
import stealmysheep.common.services.IWeaponsService;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @author frmik18
 */
@ServiceProviders({
        @ServiceProvider(service = IPlugin.class),
        @ServiceProvider(service = IUpdate.class),
        @ServiceProvider(service = IPostUpdate.class),
        @ServiceProvider(service = IWeaponsService.class)
})
public class WeaponsPlugin implements IPlugin, IUpdate, IPostUpdate, IWeaponsService {
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
    public void postUpdate(GameData gameData, World world) {
        world.getEntities(Swing.class).forEach(swing -> {
            swing.updateSwingTime(gameData);
            if (swing.getSwingTime() >= swing.getMaxSwingTime()) {
                updateSwingPosition(swing);
            } else {
                world.removeEntity(swing);
            }
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
    public void swingWeapon(World world, Entity wielder) {
        MeleeWeapon weapon = wielder.getComponent(MeleeWeapon.class);
        Position wPosition = wielder.getComponent(Position.class);
        if (weapon == null) {
            throw new IllegalStateException(wielder + " has no MeleeWeapon");
        } else if (wPosition == null) {
            throw new IllegalStateException(wielder + " has no Position");
        }

        Swing swing = new Swing(
                weapon.getSwingTime(),
                weapon.getRadius(),
                weapon.getSwingArc(),
                wPosition
        );
        swing.addComponent(new Position(0, 0, 0));
        updateSwingPosition(swing);
        world.addEntity(swing);
    }

    private void updateSwingPosition(Swing swing) {
        Position position = swing.getComponent(Position.class);

        // Calculate the new rotation relative to the origin and world
        double swingFactor = swing.getSwingTime() / swing.getMaxSwingTime();
        double relRot = swingFactor * swing.getArc() - swing.getArc()/2;
        double actualRot = swing.getOrigin().getRadians() + relRot;

        // Calculate the new position relative to the world
        Point rotationUnit = new Point((float) cos(actualRot), (float) sin(actualRot));
        Point newPos = rotationUnit.multiply((float) swing.getRadius())
                .plus(swing.getOrigin().toPoint());

        position.set(newPos);
        position.setRadians((float) actualRot);
    }
}
