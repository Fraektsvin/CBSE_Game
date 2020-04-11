/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.weapon;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Projectile;
import stealmysheep.common.assets.entityComponents.MeleeWeapon;
import stealmysheep.common.assets.entityComponents.ProjectileComponent;
import stealmysheep.common.assets.entityComponents.RangedWeapon;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;

/**
 *
 * @author nadinfariss
 */
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

        updateProjectiles(gameData, world);

    }

    private void updateProjectiles(GameData gameData, World world) {

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
        Projectile projectile = new Projectile(400);
        ProjectileComponent projectileComponen = new ProjectileComponent(entity.getId(), 50, 3);
        projectile.addComponent(projectileComponen);
        return projectile;

    }

    private boolean checkForHit(ProjectileComponent projectile, World world) {
//                     for every Entity in world:
//                   if entity has BoxCollider:
//                         if entity has Health:
//                               //damage entity
//                         remove projectile from world
        return false;
    }

}
