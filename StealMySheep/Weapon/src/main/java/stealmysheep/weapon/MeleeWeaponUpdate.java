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
import stealmysheep.common.assets.entityComponents.BoxCollider;
import stealmysheep.common.assets.entityComponents.MeleeWeapon;
import stealmysheep.common.assets.entityComponents.Position;
import stealmysheep.common.game.GameData;
import stealmysheep.common.game.World;
import stealmysheep.common.services.IUpdate;

/**
 *
 * @author nadinfariss
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IUpdate.class),})
public class MeleeWeaponUpdate implements IUpdate {

    @Override
    public void update(GameData gameData, World world) {

        for (Entity entity : world.getEntities()) {

            if (entity.hasComponent(MeleeWeapon.class)) {
                updateMeleeWeapon(gameData, world, entity);
            }
        }
    }

    private void updateMeleeWeapon(GameData gameData, World world, Entity entity) {
        MeleeWeapon meleeweapon = entity.getComponent(MeleeWeapon.class);
        if (meleeweapon.isIsAttacking()) {
            for (Entity hittingEntity : world.getEntities()) {
                if (entity.getId().equals(hittingEntity.getId())) {
                    continue;
                }
                meleeHit(entity, hittingEntity);
            }
            meleeweapon.setIsAttacking(false);

        }
    }

    private boolean meleeHit(Entity entity, Entity hittingEntity) {
        MeleeWeapon meleeweapon = entity.getComponent(MeleeWeapon.class);
        Position position = entity.getComponent(Position.class);
        float radians = position.getRadians();
        float x = position.getX();
        float y = position.getY();

        float endX = x + (float) (meleeweapon.getRange() * cos(radians));
        float endY = y + (float) (meleeweapon.getRange() * sin(radians));

        if (hittingEntity.hasComponent(BoxCollider.class) && hittingEntity.hasComponent(Position.class)) {
            Position hittingPosition = hittingEntity.getComponent(Position.class);
            BoxCollider collider = hittingEntity.getComponent(BoxCollider.class);

            float x1 = hittingPosition.getX() - collider.getWidth() / 2;
            float x2 = hittingPosition.getX() + collider.getWidth() / 2;
            float y1 = hittingPosition.getY() - collider.getHeight() / 2;
            float y2 = hittingPosition.getY() + collider.getHeight() / 2;

            if (x1 > endX || x2 < x) {
                return false;
            }

            if (y2 < y || y1 > endY) {
                return false;
            }
        }
        return true;

    }

}
