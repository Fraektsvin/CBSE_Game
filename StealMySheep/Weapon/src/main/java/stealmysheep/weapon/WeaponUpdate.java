/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stealmysheep.weapon;

import stealmysheep.common.assets.Entity;
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
        
        for (Projectile projectile : world.getEntities(Projectile.class)) {
            updateProjectiles(gameData, world, projectile);
        }
        
    }
    
    private void updateProjectiles(GameData gameData, World world, Projectile projectile) {
        
    }
    
    private void updateMeleeWeapon(GameData gameData, World world, Entity entity) {
        
    }
    
    private void updateRangedWeapon(GameData gameData, World world, Entity entity) {

        if (RangedWeapon) //        if rangedWeapon.isAttacking is true:
        //                  createProjectile():
        //                  set rangedWeapon.isAttacking to false
        //
        //      Projectile createProjectile():
        //             return projectile
        {

        }
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
