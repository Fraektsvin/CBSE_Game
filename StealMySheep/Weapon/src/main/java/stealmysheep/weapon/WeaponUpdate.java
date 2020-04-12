/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.weapon;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Projectile;
import stealmysheep.common.assets.entityComponents.MeleeWeapon;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.assets.entityComponents.ProjectileComponent;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;

/**
 *
 * @author nadinfariss
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})
public class WeaponUpdate implements IUpdate {

    @Override

    public void update(GameData gameData, World world) {

        for (Entity entity : world.getEntities()) {
            if (entity.hasComponent(RangedWeapon.class)) {
                updateRangedWeapon(gameData, world, entity);
            }

            if (entity.hasComponent(MeleeWeapon.class)) {
                updateMeleeWeapon(gameData, world, entity);
            }

        }

        for (Entity projectile : world.getEntities(Projectile.class)) {
            Projectile projectileEntity = (Projectile) projectile;
            updateProjectile(gameData, world, projectileEntity);

        }

    }

    private void updateProjectile(GameData gameData, World world, Projectile projectile) {
        ProjectileComponent projectileComponent = projectile.getComponent(ProjectileComponent.class);
        Position position = projectile.getComponent(Position.class);

        if (projectileComponent == null && position == null) {
            return;
        }

        if (projectileComponent.getRemove()) {
            world.removeEntity(projectile);
            return;
        }

        float x = position.getX();
        float y = position.getY();
        float radians = position.getRadians();
        float dt = gameData.getDeltaTime();
        float speed = projectile.getSpeed();

        position.setX(x + (float) cos(radians) * speed * dt);
        position.setY(y + (float) sin(radians) * speed * dt);

        projectileComponent.update(projectile, gameData);

    }

    private void updateMeleeWeapon(GameData gameData, World world, Entity entity) {

    }

    private void updateRangedWeapon(GameData gameData, World world, Entity entity) {
        RangedWeapon rangedWeapon = entity.getComponent(RangedWeapon.class);
        if (rangedWeapon.isIsAttacking()) {
            world.addEntity(createProjectile(entity));
            rangedWeapon.setIsAttacking(false);
        }
    }

    private Projectile createProjectile(Entity entity) {
        System.out.println("Creating projectile");
        Position entityPosition = entity.getComponent(Position.class);

        Projectile projectile = new Projectile(600f, "thief.png");
        Position position = new Position(entityPosition.getX(), entityPosition.getY(), entityPosition.getRadians());
        ProjectileComponent projectileComponen = new ProjectileComponent(entity.getId(), 50, 3);

        System.out.println(entityPosition.getRadians());

        projectile.addComponent(projectileComponen);
        projectile.addComponent(position);
        return projectile;

    }

    private boolean checkForHit(Projectile projectile, World world) {

//                     for every Entity in world:
//                   if entity has BoxCollider:
//                         if entity has Health:
//                               //damage entity
//                         remove projectile from world
        return false;
    }

}
