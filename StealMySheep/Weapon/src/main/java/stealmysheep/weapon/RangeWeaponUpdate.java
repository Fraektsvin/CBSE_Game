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
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.Health;
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
public class RangeWeaponUpdate implements IUpdate {

    @Override
    public void update(GameData gameData, World world) {

        for (Entity entity : world.getEntities()) {
            if (entity.hasComponent(RangedWeapon.class)) {
                updateRangedWeapon(gameData, world, entity);
            }

        }

        for (Entity projectile : world.getEntities(Projectile.class)) {
            Projectile projectileEntity = (Projectile) projectile;
            updateProjectile(gameData, world, projectileEntity);

        }

    }

    private void updateRangedWeapon(GameData gameData, World world, Entity entity) {
        RangedWeapon rangedWeapon = entity.getComponent(RangedWeapon.class);
        if (rangedWeapon.isIsAttacking()) {
            world.addEntity(createProjectile(entity));
            rangedWeapon.setIsAttacking(false);
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

        projectileHit(projectile, world);

    }

    private Projectile createProjectile(Entity entity) {

        Position entityPosition = entity.getComponent(Position.class);
        RangedWeapon rangedWeapon = entity.getComponent(RangedWeapon.class);

        Projectile projectile = new Projectile(600f, "projectile.png");
        Position position = new Position(entityPosition.getX(), entityPosition.getY(), entityPosition.getRadians());
        ProjectileComponent projectileComponen = new ProjectileComponent(entity.getId(), rangedWeapon.getDamage(), 5);

        projectile.addComponent(projectileComponen);
        projectile.addComponent(position);

        return projectile;
    }

    private void projectileHit(Projectile projectile, World world) {

        for (Entity entity : world.getEntities()) {
            ProjectileComponent projectileComponent = projectile.getComponent(ProjectileComponent.class);
            if (entity.getId().equals(projectileComponent.getSourceId())) {
                continue;
            }

            if (entity.hasComponent(BoxCollider.class) && entity.hasComponent(Position.class)) {
                Position position = entity.getComponent(Position.class);
                BoxCollider collider = entity.getComponent(BoxCollider.class);
                Position projectilePosition = projectile.getComponent(Position.class);

                float x = projectilePosition.getX();
                float y = projectilePosition.getY();

                float x1 = position.getX() - collider.getWidth() / 2;
                float x2 = position.getX() + collider.getWidth() / 2;
                float y1 = position.getY() - collider.getHeight() / 2;
                float y2 = position.getY() + collider.getHeight() / 2;

                if (x > x1 && x < x2 && y > y1 && y < y2) {
                    world.removeEntity(projectile);
                    if (entity.hasComponent(Health.class)) {
                        Health health = entity.getComponent(Health.class);
                        health.damage(projectileComponent.getDamage());

                    }
                }
            }
        }
    }

}
