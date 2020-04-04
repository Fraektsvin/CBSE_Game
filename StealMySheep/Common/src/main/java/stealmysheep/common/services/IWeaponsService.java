package stealmysheep.common.services;

import stealmysheep.common.assets.Entity;
import stealmysheep.common.assets.Projectile;
import stealmysheep.common.game.Point;
import stealmysheep.common.game.World;

/**
 * @author frmik18
 */
public interface IWeaponsService {
    /**
     * Fires a ranged weapon.
     * Does not deal with any reload or cooldown logic.
     *
     * @param alignment if the projectile belongs to the player or the enemies
     * @throws IllegalStateException if the wielder does not have a RangedWeapon
     */
    void fireWeapon(World world, Entity wielder, Projectile.Alignment alignment, Point target);

    /**
     * Swings a weapon in an arc.
     *
     * @throws IllegalStateException if the wielder does not have a MeleeWeapon and a Position
     */
    void swingWeapon(World world, Entity wielder);
}
